package com.rex.crm;

import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.AbstractItem;
import org.apache.wicket.markup.html.panel.Fragment;
import org.apache.wicket.markup.repeater.RepeatingView;
import org.apache.wicket.model.Model;

import com.rex.crm.beans.CRMUser;
import com.rex.crm.beans.UserPosition;
import com.rex.crm.db.DAOImpl;

public class SelectPositionPage extends TemplatePage{
	/**
	 * Constructor
	 */
	public SelectPositionPage(List<UserPosition> list){
	    //遍历数组，添加按钮
    	RepeatingView dataRowRepeater = new RepeatingView("dataRowRepeater");
		AbstractItem item = new AbstractItem(dataRowRepeater.newChildId());
        dataRowRepeater.add(item);
        RepeatingView columnRepeater = new RepeatingView("columnRepeater");
    	SignIn2Session session = (SignIn2Session)getSession();
    	for(UserPosition position:list){
    		//根据岗位ID获取岗位名称
    		CRMUser user = DAOImpl.getCRMUserInfoById(position.getPositionId());
    		AbstractItem columnitem = new AbstractItem(columnRepeater.newChildId(), new Model());
    		ButtonFragment btnFragment = new ButtonFragment("celldatafield","buttonFragment",this,user.getName(),String.valueOf(position.getPositionId()));
    		columnitem.add(btnFragment);
    		columnRepeater.add(columnitem);
    	}
    	item.add(columnRepeater);
        add(dataRowRepeater);
	}
	  private class ButtonFragment extends Fragment {

	        public ButtonFragment(String id, String markupId,MarkupContainer markupProvider,String value,final String positionId){
	        	super(id, markupId, markupProvider);
	        	Link link = new Link("button"){
	        		@Override
	                 public void onClick() {
	        			 SignIn2Session   session =  (SignIn2Session) getSession();
	                     session.setPositionId(positionId);
	                 	setResponsePage(getApplication().getHomePage());
	                 }
	        	};
	        	link.add(new Label("positionName",value));
	        	add(link);
	        }
    }
}
