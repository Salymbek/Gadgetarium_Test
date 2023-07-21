package peaksoft.house.gadgetariumb9.template;

import peaksoft.house.gadgetariumb9.dto.response.InfographicsResponse;

public interface JdbcTemplateRepository {
  InfographicsResponse info (String period);
}
