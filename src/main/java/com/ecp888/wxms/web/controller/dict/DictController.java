package com.ecp888.wxms.web.controller.dict;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;
import com.ecp888.wxms.vo.user.UserVO;
import com.ecp888.wxms.web.controller.common.BaseController;

@Controller
@RequestMapping(value = "/dict")
public class DictController extends BaseController {

	Log log = LogFactory.getLog(DictController.class);
	
	@Autowired
	private IDictionaryService dictionaryService;
	
	/**
	 * 分页查询数据字典类别信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/findDictList")
	public String findDictionaryList(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		DictionaryVO dictionaryVO = dictForm.getDictionaryVO();
		List<DictionaryVO> dictionaryList = dictionaryService.findDictionaryList(dictionaryVO,pageVO);
		dictForm.setDictionaryList(dictionaryList);
		map.put("dictForm", dictForm);
		return "dict/listDictionary";
	}
	
	/**
	 * 查询单条数据字典类别信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/editDict")
	public String editDictionary(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		DictionaryVO dictionaryVO = dictForm.getDictionaryVO();
		if(null!=dictionaryVO && null!=dictionaryVO.getDictionaryId()){
			dictionaryVO = dictionaryService.findDictionary(dictionaryVO.getDictionaryId());
		}
		dictForm.setDictionaryVO(dictionaryVO);
		map.put("dictForm", dictForm);
		return "dict/editDictionary";
	}
	
	/**
	 * 保存数据字典信息
	 * @author liuhaibo
	 * @since 2014-07-16
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveDict")
	public String saveDictionary(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		DictionaryVO dictionaryVO = dictForm.getDictionaryVO();
		UserVO userVO=getUserInfo();
		dictionaryVO.setCreateUser(userVO.getUserId());
		dictionaryVO.setUpdateUser(userVO.getUserId());
		dictionaryService.saveDictionary(dictionaryVO);
		List<DictionaryVO> dictionaryList = dictionaryService.findDictionaryList(dictionaryVO,pageVO);
		dictForm.setDictionaryList(dictionaryList);
		map.put("dictForm", dictForm);
		return "dict/listDictionary";
	}
	
	/**
	 * 删除单条数据字典类别信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/deleteDict")
	public String deleteDictionary(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		DictionaryVO dictionaryVO = dictForm.getDictionaryVO();
		dictionaryService.deleteDictionary(dictionaryVO.getDictionaryId());
		List<DictionaryVO> dictionaryList = dictionaryService.findDictionaryList(dictionaryVO,pageVO);
		dictForm.setDictionaryList(dictionaryList);
		map.put("dictForm", dictForm);
		return "dict/listDictionary";
	}
	
	/**
	 * 分页查询数据字典类别子项信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/findDictItemList")
	public String findDictItemList(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		DictionaryVO dictionaryVO = dictForm.getDictionaryVO();
		DictionaryItemVO dictItemVO = dictForm.getDictItemVO();
		if(null==dictItemVO.getDictionaryId()){
			dictItemVO.setDictionaryId(dictionaryVO.getDictionaryId());
		}
		List<DictionaryItemVO> dictItemList = dictionaryService.findDictItemList(dictItemVO,pageVO);
		dictForm.setDictItemList(dictItemList);
		map.put("dictForm", dictForm);
		return "dict/listDictItem";
	}
	
	/**
	 * 查询单条数据字典类别子项信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/editDictItem")
	public String editDictItem(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		DictionaryItemVO dictItemVO = dictForm.getDictItemVO(); 
		if(null!=dictItemVO && null!=dictItemVO.getItemId()){
			dictItemVO = dictionaryService.findDictItem(dictItemVO.getItemId());
		}
		dictForm.setDictItemVO(dictItemVO);
		map.put("dictForm", dictForm);
		return "dict/editDictItem";
	}
	
	/**
	 * 保存数据字典子项信息
	 * @author liuhaibo
	 * @since 2014-07-16
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveDictItem")
	public String saveDictItem(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			)throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		UserVO userVO=getUserInfo();
		DictionaryItemVO dictItemVO = dictForm.getDictItemVO();
		dictItemVO.setCreateUser(userVO.getUserId());
		dictItemVO.setUpdateUser(userVO.getUserId());
		dictionaryService.saveDictItem(dictItemVO);
		List<DictionaryItemVO> dictItemList = dictionaryService.findDictItemList(dictItemVO,pageVO);
		if(!dictItemList.isEmpty()){
			dictForm.setDictItemList(dictItemList);
		}
		map.put("dictForm", dictForm);
		return "dict/listDictItem";
	}
	
	/**
	 * 删除单条数据字典类别子项信息
	 * @return
	 * @author ecp875
	 * @since 2014-07-16
	 */
	@RequestMapping(value = "/deleteDictItem")
	public String deleteDictItem(ModelMap map,@ModelAttribute("dictForm") DictForm dictForm
			) throws Exception{
		PageVO pageVO = dictForm.getPageVO();
		DictionaryItemVO dictItemVO = dictForm.getDictItemVO();
		dictionaryService.deleteDictItem(dictItemVO.getItemId());
		List<DictionaryItemVO> dictItemList = dictionaryService.findDictItemList(dictItemVO,pageVO);
		dictForm.setDictItemList(dictItemList);
		map.put("dictForm", dictForm);
		return "dict/listDictItem";
	}
	
}
