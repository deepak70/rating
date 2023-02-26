package com.ates.rating.app.repository;

import com.ates.rating.app.model.ClassEntity;
import com.ates.rating.app.model.SemesterEntity;
import com.ates.rating.app.model.SubjectMasterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMasterEntityRepository extends JpaRepository<SubjectMasterEntity, Long> {
    List<SubjectMasterEntity> findBySemesterEntityAndClassEntityAndPattern(SemesterEntity semesterEntity, ClassEntity classEntity, String year);

    List<SubjectMasterEntity> findBySemesterEntityAndClassEntity(SemesterEntity semesterEntity, ClassEntity classEntity);
}
