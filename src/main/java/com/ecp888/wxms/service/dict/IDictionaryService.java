/**
 * 数据字典类别及其子项接口
 */
package com.ecp888.wxms.service.dict;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.springframework.transaction.annotation.Transactional;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.dict.DictionaryItemVO;
import com.ecp888.wxms.vo.dict.DictionaryVO;

/**
 * @author ecp875
 * @since 2014-07-07
 */
@Transactional
@Consumes("application/json")
@Produces({"application/json","application/xml"})
@Path("/dictionary")
public interface IDictionaryService {
	
	/**
	 * 分页查询数据字典类别信息
	 * @param dictionaryVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	public List<DictionaryVO> findDictionaryList(DictionaryVO dictVO,PageVO pageVO) throws AppException;
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
	@GET
	@Path("/dict/{dictId}")
	public DictionaryVO findDictionary(@PathParam("dictId")Integer dictionaryId) throws AppException;
	/**
	 * 查询单条数据字典类别子项信息
	 * @param dictionaryItemVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-07
	 */
	@GET
	@Path("/dictItem/{itemId}")
	public DictionaryItemVO findDictItem(@PathParam("itemId")Integer itemId) throws AppException;
	
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
	 * @throws AppException
	 */
	@GET
	@Path("/lookupDict/{dictType}/{parentId}")
	public List<DictionaryItemVO> lookupDict(@PathParam("dictType")String dictType,
			@PathParam("parentId")String parentId)throws AppException;
	
	public DictionaryItemVO lookupDict(String dictType,String parentId,String dictCode)throws AppException;
	
	public void saveDictionary(DictionaryVO dictionaryVO)throws AppException;
	
	public void saveDictItem(DictionaryItemVO dictItemVO)throws AppException;
	
	/**
	 * 查找数据字典树
	 * @param dictId
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/dictTree/{dictId}")
	public List<DictionaryVO> findDictTree(@PathParam("dictId")String dictId)throws AppException;
	
	/**
	 * 查找数据字典树
	 * @param dictId
	 * @return
	 * @throws AppException
	 */
	@GET
	@Path("/dictItemTree/{itemId}")
	public List<DictionaryItemVO> findDictItemTree(@PathParam("itemId")String itemId)throws AppException;
}
