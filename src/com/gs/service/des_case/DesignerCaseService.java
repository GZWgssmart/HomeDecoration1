package com.gs.service.des_case;

import java.util.List;

import com.gs.bean.DesignerCase;
import com.gs.service.BaseService;

/**
 * 设计师案例表t_designer_case				
 * @author ID.LQF
 *
 */
public interface DesignerCaseService extends BaseService<String, DesignerCase>{

	/**
	 * 更改状态
	 */
	public void updateStatus(String check, String id);
	
	/**
	 * top3精选设计师案例
	 * @return
	 */
	public List<DesignerCase> desCaseTop3();
	/**
	 * top3精选设计师案例
	 * @return
	 */
	public List<DesignerCase> desCaseTop3(String id);
	
	/**
	 * 有约束的案例计数
	 * @return
	 */
	public int countCheck();
	/**
	 * 有约束的案例分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<DesignerCase> queryByPagerCheck(int pageNo, int pageSize);
	/**
	 * 有约束的案例计数
	 * @return
	 */
	public int countCheck(String id);
	/**
	 * 有约束的案例分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<DesignerCase> PagerCheck(String id, int pageNo, int pageSize);
	/**
	 * 有约束的案例计数
	 * @return
	 */
	public int countChecked(String id);
	/**
	 * 有约束的案例分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<DesignerCase> PagerChecked(String id, int pageNo, int pageSize);
	
}
