package org.zerock.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	private static final String namespace = "org.zerock.mapper.PointMapper";
	
	@Inject
	private SqlSession sqlSession;

	@Override
	public int updatePoint(String uid, int point) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("uid", uid);
		paramMap.put("point", point);
		
		return sqlSession.update(namespace + ".updatePoint", paramMap);
	}

}
