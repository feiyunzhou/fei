package com.rex.crm.common.tree;

import java.util.List;

import com.google.common.base.Splitter;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserInfo;
import com.rex.crm.db.DAOImpl;

public class TreeFactory {

   
    
    private static void appendChildren(Node node){
       
      if(node != null){
        String nodeId = node.getKey();
       // String[] splits = nodeId.split("_");
        
         List<CRMUser> crmusers = DAOImpl.getInferiorsByManagerId(nodeId);
         if(crmusers !=null && crmusers.size()>0){
             Node[] children = new Node[crmusers.size()];
             node.setFolder(true);
             node.setChildren(children);
             int i = 0;
             for(CRMUser user:crmusers){
                 List<UserInfo> userInfo= DAOImpl.getUserByPositionId(user.getId());
                 String username = "无";
                 for(UserInfo userinfo :userInfo){
                    username = userinfo.getName();
                   if(userinfo.getName().equalsIgnoreCase(null)){
                     username =  "无";
                   }else{
                     username = userinfo.getName();
                   }
                 }
                 Node nd = new Node();
                 children[i++] = nd;
                 nd.setKey(String.valueOf(user.getId()));
                 
                 String title = user.getName() +"-" + username;
                 nd.setTitle(title);
                 nd.setType("crmuser");  
                 //System.out.println(user.getCode()+":"+user.getId());
                 appendChildren(nd);
             }
         }else{
             node.setFolder(false);
             
           
         }
        
      }
       
   }
    
    public static Node createPositionTree(){
        Node root = new Node();
        root.setTitle("Root");
        root.setFolder(true);
        root.setKey("-1");
        List<CRMUser> crmusers =  DAOImpl.getCRMUserWithoutSuperior();
        
        if(crmusers !=null && crmusers.size()>0){
            Node[] children = new Node[crmusers.size()];
            root.setChildren(children);
            root.setFolder(true);
            int i = 0;
            for(CRMUser user:crmusers){
                Node nd = new Node();
                children[i++] = nd;
                nd.setKey(String.valueOf(user.getId()));
                nd.setTitle(user.getCode());
                nd.setType("crmuser");           
                appendChildren(nd);
            }
        }else{
            root.setFolder(false);           
        }
        return root;   
    }
}
