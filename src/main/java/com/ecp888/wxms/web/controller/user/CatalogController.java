package com.ecp888.wxms.web.controller.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecp888.wxms.service.user.ICatalogService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.CatalogVO;
import com.ecp888.wxms.vo.user.UserVO;
import com.ecp888.wxms.web.controller.common.BaseController;

@Controller
@RequestMapping(value = "/catalog")
public class CatalogController extends BaseController{

	Log log = LogFactory.getLog(CatalogController.class);
	
	@Autowired
	private ICatalogService catalogService;
	
	/**
	 * 分页查询栏目信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/findCatalogList")
	public String findCatalogList(ModelMap map,@ModelAttribute("catalogForm") CatalogForm catalogForm
			)throws Exception{
		PageVO pageVO = catalogForm.getPageVO();
		CatalogVO catalogVO = catalogForm.getCatalogVO();
		List<CatalogVO> catalogList = catalogService.findCatalogList(catalogVO,pageVO);
		catalogForm.setCatalogList(catalogList);
		map.put("catalogForm", catalogForm);
		return "user/listCatalog";
	}
	
	/**
	 * 查询单条栏目信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/editCatalog")
	public String editCatalog(ModelMap map,@ModelAttribute("catalogForm") CatalogForm catalogForm
			)throws Exception{
		CatalogVO catalogVO = catalogForm.getCatalogVO();
		if(null!=catalogVO && null!=catalogVO.getCatalogId()){
			catalogVO = catalogService.findCatalog(catalogVO.getCatalogId());
		}
		catalogForm.setCatalogVO(catalogVO);
		map.put("catalogForm", catalogForm);
		return "user/editCatalog";
	}
	
	/**
	 * 保存栏目信息
	 * @author liuhaibo
	 * @since 2014-07-16
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveCatalog")
	public String saveCatalog(ModelMap map,@ModelAttribute("catalogForm") CatalogForm catalogForm
			)throws Exception{
		PageVO pageVO = catalogForm.getPageVO();
		CatalogVO catalogVO = catalogForm.getCatalogVO();
		catalogService.saveCatalog(catalogVO);
		
		List<CatalogVO> catalogList = catalogService.findCatalogList(catalogVO,pageVO);
		catalogForm.setCatalogList(catalogList);
		map.put("catalogForm", catalogForm);
		return "user/listCatalog";
	}
	
	/**
	 * 删除单条栏目信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/deleteCatalog")
	public String deleteCatalog(ModelMap map,@ModelAttribute("catalogForm") CatalogForm catalogForm
			)throws Exception{
		PageVO pageVO = catalogForm.getPageVO();
		CatalogVO catalogVO = catalogForm.getCatalogVO();
		catalogService.deleteCatalog(catalogVO.getCatalogId());
		List<CatalogVO> catalogList = catalogService.findCatalogList(catalogVO,pageVO);
		catalogForm.setCatalogList(catalogList);
		map.put("catalogForm", catalogForm);
		return "user/listCatalog";
	}
	
}
