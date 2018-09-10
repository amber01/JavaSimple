package com.mmail.controller.backend;

import com.mmail.common.Const;
import com.mmail.common.ResponseCode;
import com.mmail.common.ServerResponse;
import com.mmail.pojo.User;
import com.mmail.service.ICategoryService;
import com.mmail.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ Author     ：tank
 * @ Date       ：Created in 12:12 2018/9/10
 * @ Description：
 * @ Modified By：
 * @Version:
 */
@RestController
@RequestMapping("manager/category")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    //defaultValue = "0"  用户没有传值的情况下，默认是0，0表示分类的根节点
    @RequestMapping(value = "add_category.do",method = RequestMethod.POST)
    public ServerResponse addCategory(HttpSession session, String categoryName, @RequestParam(value = "parentId",defaultValue = "0")int parentId){
        //判断是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }

//        if (user.getRole() == 0){
//            return ServerResponse.createByErrorMessage("对不起，没有管理员权限");
//        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员
            //正价我们处理分类的逻辑
            return iCategoryService.addCategory(categoryName,parentId);
        }

        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    //修改category name
    @RequestMapping(value = "set_category_name.do",method = RequestMethod.POST)
    public ServerResponse setCategoryName(HttpSession session,Integer categoryId,String categoryName){
        //判断是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //更新category
            return iCategoryService.updateCategoryName(categoryName,categoryId);
        }

        return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
    }

    //如果categoryId = 0(不传默认为0)，就表示获取平级的。就拿到id 100001、100002、100003、100004、100005的数据
    //获取子节点并且是平级的category信息
    @RequestMapping(value = "get_category.do",method = RequestMethod.POST)
    public ServerResponse getChildrenParalleCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId)
    {
        //判断是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询子节点category信息，并且不递归，保持平级
            return iCategoryService.getChildrenParalleCategory(categoryId);
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    //获取当前category的id，并且递归查询它的子节点的category id
    @RequestMapping(value = "get_deep_category.do",method = RequestMethod.POST)
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session,@RequestParam(value = "categoryId",defaultValue = "0")Integer categoryId){
        //判断是否登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //查询当前节点的id和递归子节点的id
            //假设parent_id是0，下面有个子节点是10000，10000下面还有个子节点100000
            //0->10000->100000->1000000
            //如果传0，10000、100000和1000000 id下的都要返回。如果传10000，100000和1000000要返回
        }else{
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
        return iCategoryService.selectCategoryAndChildrenById(categoryId);
    }
}
