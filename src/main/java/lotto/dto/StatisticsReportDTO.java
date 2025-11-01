package lotto.dto;

import java.util.List;

public record StatisticsReportDTO(List<RankResultDTO> results, double profitRate) {
}
