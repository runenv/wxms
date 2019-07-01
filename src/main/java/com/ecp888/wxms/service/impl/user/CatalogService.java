package com.ecp888.wxms.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecp888.wxms.dao.user.ICatalogDAO;
import com.ecp888.wxms.service.user.ICatalogService;
import com.ecp888.wxms.utils.AppUtil;
import com.ecp888.wxms.utils.WxConstants;
import com.ecp888.wxms.utils.exception.AppException;
import com.ecp888.wxms.utils.exception.ExConstants;
import com.ecp888.wxms.utils.exception.FunctionException;
import com.ecp888.wxms.utils.interfaces.AppOperate;
import com.ecp888.wxms.utils.interfaces.AppResource;
import com.ecp888.wxms.utils.interfaces.Audit;
import com.ecp888.wxms.vo.PageVO;
import com.ecp888.wxms.vo.user.CatalogVO;
import com.ecp888.wxms.vo.user.RoleVO;

/**
 * 栏目Service
 * @author ecp875
 * @since 2014-07-17
 */
@Service("catalogService")
@AppResource(code="CatalogService",desc="栏目管理")
public class CatalogService implements ICatalogService {
	@Autowired	 
	ICatalogDAO catalogDao;
	
	/**
	 * 分页查询栏目类别信息
	 * @param customerVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public List<CatalogVO> findCatalogList(CatalogVO catalogVO,PageVO pageVO)
			throws AppException {
		pageVO.setRecordTotal(catalogDao.countCatalogList(catalogVO));
		List<CatalogVO> catalogList = catalogDao.findCatalogList(catalogVO,pageVO);
		return catalogList;
	}
	
	/**
	 * 查询单条栏目类别信息
	 * @param catalogVO
	 * @return
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public CatalogVO findCatalog(Integer catalogId) throws AppException {
		
		CatalogVO catalogVO = catalogDao.findCatalog(catalogId);
		List<String> roleIds = catalogDao.findRoleCatalog(catalogId);
		String[] ids=new String[roleIds.size()];
		roleIds.toArray(ids);
		if(null!=roleIds && !roleIds.isEmpty()){
			catalogVO.setArrId(ids);
		}
		return catalogVO;
	}
	
	/**
	 * 删除栏目类别信息
	 * @param customerList
	 * @throws AppException
	 * @author ecp875
	 * @since 2014-07-17
	 */
	@AppOperate(code="delete",desc="删除栏目")
	public void deleteCatalog(Integer catalogId)  throws AppException{
		if(WxConstants.APP_RESOURCE_POINT > catalogId){
			throw new FunctionException(ExConstants.SYS_COMMON_10008);
		}
		catalogDao.deleteCatalog(catalogId);
	}
	
	/**
	 * 保存栏目信息
	 * @author ecp875
	 * @since 2014-07-17
	 */
	@Audit(description="添加或更新栏目信息")
	@AppOperate(code="update",desc="添加或更新栏目")
	public void saveCatalog(CatalogVO catalogVO) throws AppException {
		if(null==catalogVO) return;
		if(null==catalogVO.getParentId()){
			catalogVO.setParentId(0);
		}
		
		if(null!=catalogVO.getCatalogId()){
			catalogDao.updateCatalog(catalogVO);
		}else{
			catalogDao.createCatalog(catalogVO);
		}
		catalogVO.setRoleVO(AppUtil.getCurrentUser().getUserRoleProgramVO().getRoleVO());
		catalogDao.deleteRoleCatalog(catalogVO);
		if (null != catalogVO.getArrId() && null != catalogVO.getCatalogId()) {
			List<CatalogVO> catalogList = catalogDao
					.findCatalogTree(String.valueOf(catalogVO.getCatalogId()));
			//栏目菜单授予根节点，子节点自动授予权限
			if(null != catalogList && !catalogList.isEmpty()){
				for(CatalogVO tempVO : catalogList){
					catalogDao.deleteRoleCatalog(tempVO);
					tempVO.setArrId(catalogVO.getArrId());
					catalogDao.createRoleCatalog(tempVO);
				}
			}else{
				catalogDao.createRoleCatalog(catalogVO);
			}
		}
	}

	/**
	 * 查询树结构的栏目
	 * @author ecp875
	 * @since 2014-07-17
	 */
	public List<CatalogVO> findCatalogTree(String catalogId) throws AppException {
		return catalogDao.findCatalogTree(catalogId);
	}
	
	/**
	 * 根据parentId查询树结构的栏目
	 * @author ecp875
	 * @since 2014-07-24
	 */
	public List<CatalogVO> findCatalogTreeByParentId(String parentId) throws AppException {
		CatalogVO catalogVO = new CatalogVO();
		catalogVO.setParentId(Integer.valueOf(parentId));
		catalogVO.setRoleVO(AppUtil.getCurrentUser().getUserRoleProgramVO().getRoleVO());
		return catalogDao.findCatalogTreeByParentId(catalogVO);
	}
	
}
