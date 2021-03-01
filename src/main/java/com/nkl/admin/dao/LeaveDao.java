package com.nkl.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.nkl.admin.domain.Clazz;
import com.nkl.admin.domain.Leave;
import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;

@Repository
public class LeaveDao extends BaseDao {
	public int listLeavesCount(Leave paramsLeave) {
			int sum = 0;
			StringBuilder sBuilder = new StringBuilder();
			sBuilder.append("SELECT count(*) FROM Leave WHERE 1=1");
			List<Object> paramsList = new ArrayList<Object>();
			if (paramsLeave.getId()!=0) {
				sBuilder.append(" and id = ? ");
				paramsList.add(paramsLeave.getId());
			}
			if (!StringUtil.isEmptyString(paramsLeave.getStuname())) {
				sBuilder.append(" and stuname like '%" + paramsLeave.getStuname() +"%' ");
			}
			
			if (paramsLeave.getState()!=0) {
				sBuilder.append(" and state = ? ");
				paramsList.add(paramsLeave.getState());
			}
			
			
			Object[] params = null;
			if (paramsList.size()>0) {
				params = new Object[paramsList.size()];
				for (int i = 0; i < paramsList.size(); i++) {
					params[i] = paramsList.get(i);
				}
	}

	long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
	sum = (int)count;
	return sum;
}

public List<Leave> listLeaves(Leave paramsLeave) {

	List<Leave> leaves = null;
	StringBuilder sBuilder = new StringBuilder();
	sBuilder.append("FROM Leave WHERE 1=1");
	List<Object> paramsList = new ArrayList<Object>();
	if (paramsLeave.getId()!=0) {
		sBuilder.append(" and id = ? ");
		paramsList.add(paramsLeave.getId());
	}
	
	if (!StringUtil.isEmptyString(paramsLeave.getStuname())) {
		sBuilder.append(" and stuname like '%" + paramsLeave.getStuname() +"%' ");
	}
	
	if (paramsLeave.getState()!=0) {
		sBuilder.append(" and state = ? ");
		paramsList.add(paramsLeave.getState());
	}
	
	Object[] params = null;
	if (paramsList.size()>0) {
		params = new Object[paramsList.size()];
		for (int i = 0; i < paramsList.size(); i++) {
			params[i] = paramsList.get(i);
		}
	}

	sBuilder.append(" order by id desc ");

	List list = null;
	if (paramsLeave.getStart()!=-1) {
		list = super.findByPageHql(sBuilder.toString(), params, paramsLeave.getStart(), paramsLeave.getLimit());
	}else {
		list = super.executeQueryHql(sBuilder.toString(), params);
	}
	if (list != null && list.size() > 0) {
		leaves = new ArrayList<Leave>();
		for (Object object : list) {
			leaves.add((Leave)object);
		}
	}

	return leaves;
}

public void addLeave(Leave paramsLeave) {
	// TODO Auto-generated method stub
	super.add(paramsLeave);
}
/**
 * 	根据ID获取请假信息
 * @param paramsLeave
 * @return
 */
public Leave getLeave(Leave paramsLeave) {
	Leave leave = null;
	StringBuilder sBuilder = new StringBuilder();
	sBuilder.append("FROM Leave WHERE 1=1");
	List<Object> paramsList = new ArrayList<Object>();
	if (paramsLeave.getId()!=0) {
		sBuilder.append(" and id = ? ");
		paramsList.add(paramsLeave.getId());
	}
	
	if (!StringUtil.isEmptyString(paramsLeave.getStuname())) {
		sBuilder.append(" and stuname like '%" + paramsLeave.getStuname() +"%' ");
	}

	Object[] params = null;
	if (paramsList.size()>0) {
		params = new Object[paramsList.size()];
		for (int i = 0; i < paramsList.size(); i++) {
			params[i] = paramsList.get(i);
		}
	}
	sBuilder.append(" order by id desc ");
	List list = super.executeQueryHql(sBuilder.toString(), params);
	if (list != null && list.size() > 0) {
		leave = (Leave)list.get(0);
	}

	return leave;
}

public Leave getLeaveById(Leave paramsLeave) {
	// TODO Auto-generated method stub
	Leave leave = (Leave)super.get(Leave.class, paramsLeave.getId());
	return leave;
}



public void updateLeave(Leave paramsLeave) {
	// TODO Auto-generated method stub
	Leave leave = (Leave)super.get(Leave.class, paramsLeave.getId());
	if (!StringUtil.isEmptyString(paramsLeave.getReason())) {
		leave.setReason(paramsLeave.getReason());
	}
	if (paramsLeave.getStarttime()!=null) {
		leave.setStarttime(paramsLeave.getStarttime());
	}
	if (paramsLeave.getEndtime()!=null) {
		leave.setEndtime(paramsLeave.getEndtime());
	}
	if (paramsLeave.getState()!=-1) {
		leave.setState((paramsLeave.getState()));
	}
	super.update(leave);
}

public void delLeaves(Leave paramsLeave) {
	// TODO Auto-generated method stub
	super.del(paramsLeave);
}

}

