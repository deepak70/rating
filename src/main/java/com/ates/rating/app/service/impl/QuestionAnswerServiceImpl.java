package com.ates.rating.app.service.impl;

import com.ates.rating.app.exception.AppException;
import com.ates.rating.app.model.FeedbackList;
import com.ates.rating.app.model.SubjectMasterEntity;
import com.ates.rating.app.model.UserOption;
import com.ates.rating.app.repository.*;
import com.ates.rating.app.security.security.services.UserDetailsImpl;
import com.ates.rating.app.service.QuestionAnswerService;
import com.ates.rating.app.viewmodel.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class QuestionAnswerServiceImpl implements QuestionAnswerService {
    private final DepartmentRepository departmentRepository;
    private final ClassEntityRepository classEntityRepository;
    private final SemesterRepository semesterRepository;
    private final FeedbackAnswerRepository feedbackAnswerRepository;
    private final FeedbackQuestionRepository feedbackQuestionRepository;
    private final UserRepository userRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final FeedbackListRepository feedbackListRepository;
    private final SubjectMasterEntityRepository subjectMasterEntityRepository;

    @Override
    public QuestionAndAnswerVM getAllQuestionAndAnswer(Long userId, String type) {
        var feedbackUserType = feedbackListRepository.findByFeedbackType(type)
                .orElseThrow(() -> new AppException("Feedback type not found " + type));
        return new QuestionAndAnswerVM()
                .setFeedbackQuestionVM(getFeedbackQuestion(feedbackUserType))
                .setFeedbackOptionVM(getAllQuestionAnswer(feedbackUserType));
    }

    @Override
    public ChartDataVM getChartData(String chartType, Integer year) {
//        var options = feedbackAnswerRepository.findAllByType(chartType);
//        var question = feedbackQuestionRepository.findAllByFeedbackList(chartType);
        return null;
    }

    @Override
    public Boolean checkAlreadySubmit(UserDetailsImpl user, String feedbackType, Long year) {
        var currentUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new AppException("User not found"));
        var userOptions = userAnswerRepository.findAllByUserAndYear(currentUser, Math.toIntExact(year));
        var userOptionListForSemester = userOptions.stream()
                .filter(userOption -> Objects.nonNull(userOption.getSemesterEntity()))
                .toList();
        var userOptionListForYear = userOptions.stream()
                .filter(userOption -> Objects.isNull(userOption.getSemesterEntity()))
                .filter(userOption -> userOption.getFeedbackQuestionEntity().getFeedbackList().getFeedbackType().equals(feedbackType))
                .toList();
        if (userOptionListForYear.size() > 0)
            return true;
        else
            return userOptionListForSemester.size() > 0;
    }

    @Override
    public SubjectWiseQuestionAnswerVM getQuestionsAndAnswerWithSemester(Long userId,
                                                                         String feedbackType,
                                                                         Long semesterId,
                                                                         Long year,
                                                                         Long classId) {
        var classEntity = classEntityRepository.findById(classId)
                .orElseThrow(() -> new AppException("class id not found"));
        var semesterEntity = semesterRepository.findById(semesterId).
                orElseThrow(() -> new AppException("semester id not found "));
        List<SubjectMasterEntity> subjectMasterEntities;
        if (classId == 0 && semesterId == 0) {
            subjectMasterEntities = new ArrayList<>(subjectMasterEntityRepository.findBySemesterEntityAndClassEntity(semesterEntity, classEntity));
        } else {
            subjectMasterEntities = new ArrayList<>(subjectMasterEntityRepository.findBySemesterEntityAndClassEntityAndPattern(semesterEntity, classEntity, year.toString()));
        }
        var feedback = feedbackListRepository.findByFeedbackType(feedbackType)
                .orElseThrow(() -> new AppException("Feedback type not found "));
        return new SubjectWiseQuestionAnswerVM().setSubjectQuestionAndAnswerVMS(subjectMasterEntities.stream()
                .map(subjectMasterEntity -> new SubjectQuestionAndAnswerVM().setSubjectId(subjectMasterEntity.getId())
                        .setSubject(subjectMasterEntity.getSubject())
                        .setFeedbackOptionVMS(getAllQuestionAnswer(feedback))
                        .setFeedbackQuestionVM(getFeedbackQuestion(feedback)))
                .toList());
    }

    @Override
    public Boolean saveAnswer(UserDetailsImpl user, OptionVM optionVM) {
        try {
            var users = userRepository.findByUsername(user.getUsername())
                    .orElseThrow(() -> new AppException("User not found " + user.getUsername()));
            var semester = semesterRepository.findById(optionVM.getSemesterId())
                    .orElseThrow(() -> new AppException("Invalid semester"));
            var department = departmentRepository.findById(optionVM.getDepartmentId())
                    .orElseThrow(() -> new AppException("Department not found "));
            var classEntity = classEntityRepository.findById(optionVM.getClassId())
                    .orElseThrow(() -> new AppException("Class id not found"));
            var vm = optionVM.getSaveOptionVMS().stream()
                    .map(saveAnswerVM -> {
                        var question = feedbackQuestionRepository.findById(saveAnswerVM.getQuestionId())
                                .orElseThrow(() -> new AppException("Question not found"));
                        var answer = feedbackAnswerRepository.findById(saveAnswerVM.getSelectedOptionId()).
                                orElseThrow(() -> new AppException("Option not found"));
                        return new UserOption()
                                .setUser(users)
                                .setYear(optionVM.getYear())
                                .setFeedback(question.getFeedbackList())
                                .setFeedbackAnswer(answer)
                                .setSemesterEntity(semester)
                                .setDepartment(department)
                                .setClassEntity(classEntity)
                                .setFeedbackQuestionEntity(question);
                    }).toList();

            userAnswerRepository.saveAll(vm);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(e.getMessage());
        }
    }

    private List<FeedbackOptionVM> getAllQuestionAnswer(FeedbackList feedbackUserType) {
        var feedbackAnswerEntities = feedbackAnswerRepository.findAllByFeedbackList(feedbackUserType);
        return feedbackAnswerEntities.stream()
                .map(feedbackAnswerEntity -> new FeedbackOptionVM()
                        .setOption(feedbackAnswerEntity.getAnswer())
                        .setOptionId(feedbackAnswerEntity.getId()))
                .collect(Collectors.toList());
    }

    private List<FeedbackQuestionVM> getFeedbackQuestion(FeedbackList feedbackUserType) {
        var feedbackQuestionEntities = feedbackQuestionRepository.findAllByFeedbackList(feedbackUserType);
        return feedbackQuestionEntities.stream()
                .map(feedbackQuestionEntity -> new FeedbackQuestionVM()
                        .setQuestion(feedbackQuestionEntity.getQuestion())
                        .setQuestionId(feedbackQuestionEntity.getId()))
                .collect(Collectors.toList());
    }
}
