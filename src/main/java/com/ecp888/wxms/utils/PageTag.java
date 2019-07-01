package com.ecp888.wxms.utils;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import com.ecp888.wxms.vo.PageVO;

/**
 * 分页标签类
 * @author ecp875
 * @since 2014-07-07
 */
public class PageTag extends TagSupport{

	private static final long serialVersionUID = 3879280038512488156L;
	private String formName="";
	private int pageSize;
	PageVO pageVO = new PageVO();

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}
	
	public PageVO getPageVO() {
		return pageVO;
	}

	public void setPageVO(PageVO pageVO) {
		this.pageVO = pageVO;
	}

	@Override
	public int doStartTag() throws JspException {

		try {
			JspWriter out = pageContext.getOut();
			ServletRequest request = pageContext.getRequest();
			Object object = request.getAttribute(formName);
			try {
				Field field = object.getClass().getDeclaredField("pageVO");
				if(null != field){
					field.setAccessible(true);
					this.pageVO = (PageVO)field.get(object);
				}
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			
			StringBuilder str = new StringBuilder("");
			if(0!=pageSize)pageVO.setPageSize(pageSize);
			str.append("<div>");
			str.append("共"+pageVO.getRecordTotal()+"条记录").append(" | ");
			str.append("<input type=\"hidden\" name=\"pageVO.pageSize\" value=\""+pageVO.getPageSize()+"\" size=\"3\" />");
			str.append("共"+pageVO.getPageNo()+"/"+pageVO.getPageTotal()+"页").append(" | ");
			str.append("<a href=\"javascript:pageFirst();\">首页</a>").append(" | ");
			
			if(pageVO.getPageTotal() == 1){
				str.append("上一页").append(" | ");
				str.append("下一页").append(" | ");
			}else if(pageVO.getPageNo() == 1){
				str.append("上一页").append(" | ");
				str.append("<a href=\"javascript:pageNext();\">下一页</a>").append(" | ");
			}else if(pageVO.getPageNo() == pageVO.getPageTotal()){
				str.append("<a href=\"javascript:pagePre();\">上一页</a>").append(" | ");
				str.append("下一页").append(" | ");
			}else{
				str.append("<a href=\"javascript:pagePre();\">上一页</a>").append(" | ");
				str.append("<a href=\"javascript:pageNext();\">下一页</a>").append(" | ");
			}
			
			str.append("<a href=\"javascript:pageLast();\">尾页</a>").append(" | ");
			str.append("第<input name=\"pageVO.pageNo\" value=\""+pageVO.getPageNo()+"\" onblur=\"this.value=this.value.replace(/\\D/g,'');if(this.value=='')this.value=1\" size=\"2\" />页");
			str.append("<input type=\"button\" onclick=\"javascript:pageGo();\" value=\"GO\"/>");
			str.append("</div>");
			
			str.append("<script type=\"text/javascript\">");
			str.append("function pageGo(){");
			str.append("pageValidate();");
			str.append("document.forms[\""+formName+"\"].submit();");
			str.append("}");
			str.append("function pageFirst(){");
			str.append("document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value = 1;");
			str.append("document.forms[\""+formName+"\"].submit();");
			str.append("}");
			str.append("function pagePre(){");
			str.append("var pageNo = document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value;");
			str.append("document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value = parseInt(pageNo) - 1;");
			str.append("pageValidate();");
			str.append("document.forms[\""+formName+"\"].submit();");
			str.append("}");
			str.append("function pageNext(){");
			str.append("var pageNo = document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value;");
			str.append("document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value = parseInt(pageNo) + 1;");
			str.append("pageValidate();");
			str.append("document.forms[\""+formName+"\"].submit();");
			str.append("}");
			str.append("function pageLast(){");
			str.append("document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value = \""+pageVO.getPageTotal()+"\";");
			str.append("document.forms[\""+formName+"\"].submit();");
			str.append("}");
			str.append("function pageValidate(){");
			str.append("var pageTotal = "+pageVO.getPageTotal()+";");
			str.append("var pageNo = document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value;");
			str.append("if (pageNo<1)pageNo=1;");
			str.append("if (pageNo>pageTotal)pageNo=pageTotal;");
			str.append("document.forms[\""+formName+"\"][\"pageVO.pageNo\"].value = pageNo;");
			str.append("}");
			str.append("</script>");
			
			out.write(str.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//return super.doStartTag();
		// return SKIP_PAGE; //表示不处理接下来的JSP网页
		return EVAL_PAGE; // 表示处理完标签后继续执行以下的JSP网页  
		  
	}

}
