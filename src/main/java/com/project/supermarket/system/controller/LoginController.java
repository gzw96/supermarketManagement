package com.project.supermarket.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.project.supermarket.common.web.ExtAjaxResponse;
import com.project.supermarket.common.web.SessionUtil;
import com.project.supermarket.menu.entity.TreeNode;
import com.project.supermarket.menu.service.IMenuService;



//@SessionAttributes()

@RestController
public class LoginController {
 	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private IMenuService menuService;
    
 /** 登录系统**/

    @RequestMapping(value = "/login")
    public @ResponseBody ExtAjaxResponse logon(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session)
    {
    	logger.debug("logon request: {userName={}, password={}}", userName, DigestUtils.md5DigestAsHex((password).getBytes()));
        boolean checkPassword = identityService.checkPassword(userName,DigestUtils.md5DigestAsHex((password).getBytes()) );
        if (checkPassword) {
            // 查看用户是否存在
            User user = identityService.createUserQuery().userId(userName).singleResult();
            SessionUtil.setUser(session, user);
	  
	        //读取角色Group
            List<Group> groupList = identityService.createGroupQuery().groupMember(user.getId()).list();

            SessionUtil.setGroupList(session, groupList);

            String[] groupNames = new String[groupList.size()];
            for (int i = 0; i < groupNames.length; i++) {
                groupNames[i] = groupList.get(i).getName();
            }
            SessionUtil.setGroupNames(session, ArrayUtils.toString(groupNames));//"groupNames"  : "admin,hrManager"
            Map<String,String> map=new HashMap<String, String>();
            map.put("userName", userName);
            map.put("groupName", ArrayUtils.toString(groupNames));
            map.put("msg", "登录成功!");
           
            return new ExtAjaxResponse(true,map);
        } else {
        	return new ExtAjaxResponse(false,"登录失败!帐号或者密码有误!请重新登录!");
        }
    }
    /** 退出登录**/
    @RequestMapping(value = "/logout")
    public @ResponseBody ExtAjaxResponse logout(HttpSession session) 
    {
    	try {
    		SessionUtil.removeAttribute(session);
        	return new ExtAjaxResponse(true,"登出成功!");
		} catch (Exception e) {
			return new ExtAjaxResponse(false,"登出失败!");
		}
    }
    
    @RequestMapping(value = "/finduser")
    public @ResponseBody ExtAjaxResponse finduser(HttpSession session)
    {
    	String userName = SessionUtil.getUserName(session);
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("userName", userName);
    	System.out.println(userName);
    	return new ExtAjaxResponse(true,map);
    }
    
    
    @RequestMapping("/findNode")
	public @ResponseBody List<TreeNode> findNodesByParentId(@RequestParam("node") String node,@Nullable HttpSession session)
	{
		if(node.equals("root")) {
			if(String.valueOf(SessionUtil.getGroupNames(session)).equals("{SuperAdmin}")) {
				return menuService.findNodes(null);
			}
			else if(String.valueOf(SessionUtil.getGroupNames(session)).equals("{Purchase}"))
			{
				return menuService.findNodes(Long.parseLong("35"));
			}
			else if(String.valueOf(SessionUtil.getGroupNames(session)).equals("{Sale}"))
			{
				return menuService.findNodes(Long.parseLong("29"));
			}
			else if(String.valueOf(SessionUtil.getGroupNames(session)).equals("{Stock}"))
			{
				return menuService.findNodes(Long.parseLong("41"));
			}
			else if(String.valueOf(SessionUtil.getGroupNames(session)).equals("{Admin}"))
			{
				return menuService.findNodes(null);
			}
			return menuService.findNodes(null);
		}else {
			return menuService.findNodes(Long.parseLong(node));
		}
		
	}
}

