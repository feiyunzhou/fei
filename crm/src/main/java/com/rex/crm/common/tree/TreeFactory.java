package com.rex.crm.common.tree;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Splitter;
import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.Product;
import com.rex.crm.beans.ProductLine;
import com.rex.crm.beans.Productcategory;
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
                 Node nd = new Node();
                 children[i++] = nd;
                 nd.setKey(String.valueOf(user.getId()));
                 
                 String title = user.getName();
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
    
    private static void appendProductChildren(Node node){
        
        if(node != null){
          String nodeId = node.getKey();
           List<Product> products = DAOImpl.getProductByLineId(nodeId);
           if(products !=null && products.size()>0){
               Node[] children = new Node[products.size()];
               node.setFolder(true);
               node.setChildren(children);
               int i = 0;
               for(Product product:products){
                   Node nd = new Node();
                   children[i++] = nd;
                   nd.setKey(String.valueOf(product.getId()));
                   String title = product.getName();
                   nd.setTitle(title);
                   nd.setType("product");  
                  // System.out.println("newnd:"+nd);
                  //  System.out.println("newn:"+nd.getKey());
                   appendProductCategoryChildren(nd);
                   //appendProductChildren(nd);
               }
           }else{
               node.setFolder(false);
           }
          
        }
         
     }
        private static void appendProductCategoryChildren(Node node){
        
        if(node != null){
          String nodeId = node.getKey();
        //  System.out.println("appendProductCategoryChildren:"+nodeId);
           List<Productcategory> products = DAOImpl.getCategoryByLineId(nodeId);
           if(products !=null && products.size()>0){
               Node[] children = new Node[products.size()];
               node.setFolder(true);
               node.setChildren(children);
               int i = 0;
               for(Productcategory product:products){
                   Node nd = new Node();
                   children[i++] = nd;
                   nd.setKey(String.valueOf(product.getId()));
                   String title = product.getName();
                   nd.setTitle(title);
                   nd.setType("productcategory");  
               }
           }else{
               node.setFolder(false);
           }
          
        }
         
     }
    
    public static Node createPositionTree(){
        Node root = new Node();
        root.setTitle("ROOT");
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
    
    public static Node createAccountPositionTree(){
        Node root = new Node();
        root.setTitle("中国区");
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
    
    public static Node createProductTree(){
        Node root = new Node();
        root.setTitle("产品线");
        root.setFolder(true);
        root.setKey("-1");
        List<Product> productLines =  DAOImpl.getProductLineWithoutSuperior();
        
        if(productLines !=null && productLines.size()>0){
            Node[] children = new Node[productLines.size()];
            root.setChildren(children);
            root.setFolder(true);
            int i = 0;
            for(Product productLine:productLines){
                Node nd = new Node();
                children[i++] = nd;
                nd.setKey(String.valueOf(productLine.getId()));
                nd.setTitle(productLine.getName());
                nd.setType("productline"); 
                appendProductChildren(nd);
             //   System.out.println("nd:"+nd);
             //    System.out.println("node.getKey():"+nd.getKey());
            }
            
        }else{
            root.setFolder(false);           
        }
        return root;   
    }
    
}
