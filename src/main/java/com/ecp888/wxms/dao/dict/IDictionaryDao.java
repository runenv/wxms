/**
 * 数据字典类别及其子项数据库持久化类
 */
package com.ecp888.wxms.dao.dict;

import java.util.List;
import java.util.Map;

import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;

/**
 * @author ecp875
 * @since 2014-07-07
 */
public interface IDictionaryDao {

	/**
	 * 分页查询数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryVO> findDictionaryList(DictionaryVO dictVO,PageVO pageVO) throws AppException;
	
	public int countDictionaryList(DictionaryVO dictVO)throws AppException;
	
	/**
	 * 分页查询数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryItemVO> findDictItemList(DictionaryItemVO dictItemVO,PageVO pageVO) throws AppException;
	
	/**
	 * 查询单条数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public DictionaryVO findDictionary(Integer dictionaryId) throws AppException;
	/**
	 * 查询单条数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public DictionaryItemVO findDictItem(Integer itemId) throws AppException;
	
	/**
	 * 检查同一数据字典下ItemCode是否重复
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dictItemVO
	 * @return
	 * @throws AppException
	 */
	public int checkDictItem(DictionaryItemVO dictItemVO)throws AppException;
	
	/**
	 * 新建数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public int createDictionary(DictionaryVO dictionaryVO) throws AppException;
	/**
	 * 新建数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public int createDictItem(DictionaryItemVO dictItemVO) throws AppException;
	
	/**
	 * 更新数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public int updateDictionary(DictionaryVO dictionaryVO) throws AppException;
	/**
	 * 更新数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public int updateDictItem(DictionaryItemVO dictItemVO) throws AppException;
	
	/**
	 * 删除数据字典类别信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public void deleteDictionary(Integer dictionaryId)  throws AppException;
	/**
	 * 删除数据字典类别子项信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public void deleteDictItem(Integer dictItemId)  throws AppException;
	/**
	 * 根据类型查找数据字典Item
	 * @author ecp875
	 * @since 2014-07-07
	 * @param dictType
	 * @return
	 */
	public List<DictionaryItemVO> lookupDict(Map<String, Object> map)throws AppException;

	public int countDictItemList(DictionaryItemVO dictItemVO)throws AppException;

	public List<DictionaryVO> findDictTree(String dictId)throws AppException;
	
	public List<DictionaryItemVO> findDictItemTree(String itemId)throws AppException;
}
