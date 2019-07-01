/**
 * 数据字典类别及其子项的实现类
 */
package com.ecp888.wxms.service.impl.dict;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.utils.WxConstants;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.utils.interfaces.Audit;
import com.ecp888.wxms.dao.dict.IDictionaryDao;
import com.ecp888.wxms.service.dict.IDictionaryService;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;

/**
 * @author ecp875
 * @since 2014-07-07
 */
@Service("dictionaryService")
@AppResource(code="DictService",desc="数据字典管理")
public class DictionaryService implements IDictionaryService {
	@Autowired	 
	IDictionaryDao dictionaryDao;
	
	/**
	 * 分页查询数据字典类别信息
	 * @param customerVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryVO> findDictionaryList(DictionaryVO dictVO,PageVO pageVO)
			throws AppException {
		pageVO.setRecordTotal(dictionaryDao.countDictionaryList(dictVO));
		List<DictionaryVO> dictList = dictionaryDao.findDictionaryList(dictVO,pageVO);
		for(DictionaryVO dictionaryVO : dictList){
			String parentId = "0";
			DictionaryItemVO  dictItemVO = lookupDict(DictionaryVO.DICT_STATUS,
					parentId, dictionaryVO.getDictionaryStatus());
			dictionaryVO.setDictionaryStatusName(dictItemVO.getItemName());
		}
		return dictList;
	}
	
	/**
	 * 分页查询数据字典类别子项信息
	 * @param customerVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07 DictionaryItemVO dictItemVO,PageVO pageVO
	 */
	public List<DictionaryItemVO> findDictItemList(DictionaryItemVO dictItemVO,PageVO pageVO)
			throws AppException {
		pageVO.setRecordTotal(dictionaryDao.countDictItemList(dictItemVO));
		List<DictionaryItemVO> dictItemList = dictionaryDao.findDictItemList(dictItemVO,pageVO);
		for(DictionaryItemVO itemVO : dictItemList){
			DictionaryItemVO tempVO = lookupDict(DictionaryItemVO.DICT_ITEM_STATUS, 
					itemVO.getParentId().toString(),
					itemVO.getItemStatus());
			if(null!=tempVO){
				itemVO.setItemStatusName(tempVO.getItemName());
			}
		}
		return dictItemList;
	}
	/**
	 * 查询单条数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public DictionaryVO findDictionary(Integer dictionaryId) throws AppException {
		return dictionaryDao.findDictionary(dictionaryId);
	}
	/**
	 * 查询单条数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07 
	 */
	public DictionaryItemVO findDictItem(Integer itemId) throws AppException {
		return dictionaryDao.findDictItem(itemId);
	}
	
	/**
	 * 删除数据字典类别信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	@AppOperate(code="delete",desc="删除数据字典")
	public void deleteDictionary(Integer dictionaryId)  throws AppException{
		if(WxConstants.APP_RESOURCE_POINT > dictionaryId){
			throw new FunctionException(ExConstants.SYS_COMMON_10008);
		}
		dictionaryDao.deleteDictionary(dictionaryId);
	}
	/**
	 * 删除数据字典类别子项信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	@AppOperate(code="deleteItem",desc="删除数据字典子项")
	public void deleteDictItem(Integer dictItemId)  throws AppException{
		if(WxConstants.APP_RESOURCE_POINT > dictItemId){
			throw new FunctionException(ExConstants.SYS_COMMON_10008);
		}
		dictionaryDao.deleteDictItem(dictItemId);
	}
	
	/**
	 * 根据类型查找数据字典Item,返回多条记录
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryItemVO> lookupDict(String dictType,String parentId)
			throws AppException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dictionaryName", dictType);
		map.put("parentId", null == parentId || "".equals(parentId)?"0":parentId);
		return dictionaryDao.lookupDict(map) ;
	}
	
	/**
	 * 根据类型查找数据字典Item,返回单条记录
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public DictionaryItemVO lookupDict(String dictType,String parentId,String dictCode)
			throws AppException {
		DictionaryItemVO dictItemVO=new DictionaryItemVO();
		//List<DictionaryItemVO> itemList = dictionaryDao.lookupDict(dictType) ;
		List<DictionaryItemVO> itemList = lookupDict(dictType, parentId);
		if(null!=itemList && !itemList.isEmpty()){
			for(DictionaryItemVO itemVO : itemList){
				if(itemVO.getItemCode().equals(dictCode)){
					dictItemVO.setItemId(itemVO.getItemId());
					dictItemVO.setItemName(itemVO.getItemName());
					dictItemVO.setItemCode(itemVO.getItemCode());
					dictItemVO.setDictionaryId(itemVO.getDictionaryId());
					dictItemVO.setDescription(itemVO.getDescription());
					break;
				}
			}
		}
		return dictItemVO;
	}

	/**
	 * 保存数据字典信息
	 * @author ecp875
	 * @since 2014-07-07
	 */
	@Audit(description="添加或更新数据字典信息")
	@AppOperate(code="update",desc="添加或更新数据字典")
	public void saveDictionary(DictionaryVO dictionaryVO) throws AppException {
		if(null==dictionaryVO) return;
		if(null==dictionaryVO.getParentId()){
			dictionaryVO.setParentId(0);
		}
		if(null!=dictionaryVO.getDictionaryId()){
			dictionaryDao.updateDictionary(dictionaryVO);
		}else{
			dictionaryDao.createDictionary(dictionaryVO);
		}
	}

	/**
	 * 保存数据字典子项信息
	 * @author ecp875
	 * @since 2014-07-07
	 */
	@AppOperate(code="updateItem",desc="添加或更新数据字典子项")
	public void saveDictItem(DictionaryItemVO dictItemVO) throws AppException {
		if(null==dictItemVO) return;
		if(null==dictItemVO.getParentId()){
			dictItemVO.setParentId(0);
		}
		if(null!=dictItemVO.getItemId()){
			dictionaryDao.updateDictItem(dictItemVO);
		}else{
			if(checkDictItem(dictItemVO)>0){
				throw new FunctionException(ExConstants.F_USER_1005);
			}
			dictionaryDao.createDictItem(dictItemVO);
		}
	}

	/**
	 * 查询树结构的数据字典
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryVO> findDictTree(String dictId) throws AppException {
		return dictionaryDao.findDictTree(dictId);
	}
	
	/**
	 * 查询树结构的数据字典
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryItemVO> findDictItemTree(String itemId) throws AppException {
		return dictionaryDao.findDictItemTree(itemId);
	}

	/**
	 * 检查同一数据字典下ItemCode是否重复
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public int checkDictItem(DictionaryItemVO dictItemVO) throws AppException {
		return dictionaryDao.checkDictItem(dictItemVO);
	}
}
