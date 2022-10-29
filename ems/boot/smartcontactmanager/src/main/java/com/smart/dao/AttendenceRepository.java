package com.smart.dao;

import com.smart.entities.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {
//    	@Query("from Attendence as c where c.contact.code=:code")
//	public List<Attendence> findAttendenceByEmployee(@Param("code") Long code);

		@Query("from Attendence as c where c.contact.cId=:cId")
	public List<Attendence> findAttendenceByEmployee(@Param("cId") int cId);

//	@Query(value="select * from Attendence  where Attendence.contact.cId=:cId",nativeQuery = true)
//List<Attendence> findAttendenceByEmployee(Integer cId);
}
