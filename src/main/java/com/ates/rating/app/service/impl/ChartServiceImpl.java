package com.ates.rating.app.service.impl;

import com.ates.rating.app.exception.AppException;
import com.ates.rating.app.model.FeedbackAnswerEntity;
import com.ates.rating.app.model.UserOption;
import com.ates.rating.app.repository.FeedbackAnswerRepository;
import com.ates.rating.app.repository.FeedbackListRepository;
import com.ates.rating.app.repository.UserAnswerRepository;
import com.ates.rating.app.service.ChartService;
import com.ates.rating.app.viewmodel.ChartDataVM;
import com.ates.rating.app.viewmodel.ChartVM;
import com.ates.rating.app.viewmodel.OptionWeightVM;
import com.ates.rating.app.viewmodel.Options;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class ChartServiceImpl implements ChartService {
    private final FeedbackAnswerRepository feedbackAnswerRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final FeedbackListRepository feedbackListRepository;

    @Override
    public ChartDataVM getChartData(String chartType, Integer year) {

        var feedbackList = feedbackListRepository.findByFeedbackType(chartType)
                .orElseThrow(() -> new AppException("Feedback type not found " + chartType));
        var userOptions = userAnswerRepository.findAllByYearAndFeedback(year, feedbackList);
        var options = feedbackAnswerRepository.findAllByFeedbackList(feedbackList);
        var feedbackQuestionEntityMapMap = userOptions.stream()
                .collect(Collectors.groupingBy(UserOption::getFeedbackQuestionEntity,
                        Collectors.groupingBy(userOption -> userOption.getFeedbackAnswer().getAnswer(),
                                Collectors.summingInt(value -> value.getFeedbackAnswer().getWeightage()))
                ));
        var chartVMS = new ArrayList<ChartVM>();
        feedbackQuestionEntityMapMap.forEach((key, value) -> {
            var chartVM = new ChartVM()
                    .setQuestionId(key.getId())
                    .setQuestion(key.getQuestion())
                    .setOptionWeightVMS(getOptionWeight(value, options));
            chartVMS.add(chartVM);
        });
        var map = chartVMS.stream()
                .collect(Collectors.groupingBy(ChartVM::getQuestionId,
                        Collectors.summingInt(value -> value.getOptionWeightVMS().values()
                                .stream().
                                mapToInt(i -> i)
                                .sum())));

        return new ChartDataVM().setChartVMS(calculateRatingsInPercentage(chartVMS, map, options))
                .setOptions(options.stream()
                        .map(FeedbackAnswerEntity::getAnswer)
                        .collect(Collectors.toList()));
    }

    private List<ChartVM> calculateRatingsInPercentage(ArrayList<ChartVM> chartVMS, Map<Long, Integer> map, List<FeedbackAnswerEntity> options) {
        return chartVMS.stream()
                .map(chartVM -> new ChartVM()
                        .setQuestionId(chartVM.getQuestionId())
                        .setQuestion(chartVM.getQuestion())
                        .setOptionWeightVM(getOptionWeightInPercentage(chartVM, map, options)))
                .collect(Collectors.toList());
    }

    private OptionWeightVM getOptionWeightInPercentage(ChartVM chartVM, Map<Long, Integer> map, List<FeedbackAnswerEntity> options) {
        var maps = chartVM.getOptionWeightVMS();
        var objectList = options.stream()
                .map(feedbackAnswerEntity -> {
                    var answer = maps.get(feedbackAnswerEntity.getAnswer()) * 10;
                    return new Options().setOptionId(feedbackAnswerEntity.getId())
                            .setPercentage(BigDecimal.valueOf(map.get(chartVM.getQuestionId()) == 0 ? answer : answer / (map.get(chartVM.getQuestionId()) / 10)));
                }).toList();
        return new OptionWeightVM().setOptions(objectList);
    }

    private Map<String, Integer> getOptionWeight(Map<String, Integer> userOptionANdWeight, List<FeedbackAnswerEntity> options) {
        return options.stream()
                .collect(Collectors.toMap(FeedbackAnswerEntity::getAnswer,
                        feedbackAnswerEntity -> userOptionANdWeight.getOrDefault(feedbackAnswerEntity.getAnswer(), 0)));
    }
}
