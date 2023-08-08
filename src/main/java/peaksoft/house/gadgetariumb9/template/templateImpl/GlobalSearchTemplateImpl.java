package peaksoft.house.gadgetariumb9.template.templateImpl;

import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.response.globalSearch.AdminSearchResponse;
import peaksoft.house.gadgetariumb9.template.GlobalSearchTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class GlobalSearchTemplateImpl implements GlobalSearchTemplate {

  private final JdbcTemplate jdbcTemplate;

  @Override
  public List<AdminSearchResponse> search(String keyword) {

    String sql = """        
        SELECT p.id                                                              as product_id,
               sp.id                                                             as sub_product_id,
               (SELECT spi.images
               FROM sub_product_images spi
               WHERE spi.sub_product_id = sp.id
               LIMIT 1)                                                          as images,
               sp.article_number                                                 as article_number,
               p.name                                                            as name,
               p.data_of_issue                                                   as data_of_issue,
               sp.quantity                                                       as quantity,
               concat(sp.price,', ',d.sale)                                      as price_and_sale,
               SUM(sp.price * (1 - d.sale / 100.0))                              AS current_price 
        FROM products p
        JOIN sub_products sp on p.id = sp.product_id
        LEFT JOIN discounts d on sp.id = d.sub_product_id
        WHERE CAST(sp.article_number AS TEXT) ILIKE (concat('%' || ? || '%'))
                 OR CAST(p.name AS TEXT) ILIKE (concat('%' || ? || '%'))
                 OR CAST(p.data_of_issue AS TEXT) ILIKE (concat('%' || ? || '%'))  
                 OR CAST(sp.price AS TEXT) ILIKE (concat('%' || ? || '%'))
        GROUP BY p.id, sp.id, sp.article_number, p.name, p.data_of_issue, sp.quantity, sp.price, d.sale order by sp.price desc 
        """;

    return jdbcTemplate.query(sql,
        (rs, rowNum) -> {
          AdminSearchResponse response = new AdminSearchResponse();
          response.setProductId(rs.getLong("product_id"));
          response.setImages(rs.getString("images"));
          response.setArticleNumber(rs.getInt("article_number"));
          response.setName(rs.getString("name"));
          response.setDataOfIssue(rs.getDate("data_of_issue").toLocalDate());
          response.setQuantity(rs.getInt("quantity"));
          response.setPriceAndSale(rs.getString("price_and_sale"));
          response.setCurrentPrice(rs.getBigDecimal("current_price"));
          return response;
        },keyword, keyword, keyword, keyword
    );
  }
}
/*return jdbcTemplate.query(sql,
        (rs, rowNum) -> {
          AdminSearchResponse response = new AdminSearchResponse();
          response.setProductId(rs.getLong("product_id"));
          response.setImages(rs.getString("images"));
          response.setArticleNumber(rs.getInt("article_number"));
          response.setName(rs.getString("name"));
          response.setDataOfIssue(LocalDate.parse(rs.getString("data_of_issue")));
          response.setQuantity(rs.getInt("quantity"));
          response.setPriceAndSale(rs.getString("price_and_sale"));
          response.setCurrentPrice(rs.getBigDecimal("current_price"));
          return response;
        },keyword, keyword, keyword, keyword
);*/