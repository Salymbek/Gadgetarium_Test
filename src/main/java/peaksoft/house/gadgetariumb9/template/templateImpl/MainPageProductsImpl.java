package peaksoft.house.gadgetariumb9.template.templateImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.MainPagePaginationResponse;
import peaksoft.house.gadgetariumb9.dto.response.subProduct.SubProductMainPageResponse;
import peaksoft.house.gadgetariumb9.services.UtilitiesService;
import peaksoft.house.gadgetariumb9.template.MainPageProducts;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MainPageProductsImpl implements MainPageProducts {

  private final JdbcTemplate jdbcTemplate;

  private final UtilitiesService utilitiesService;

  private Map<String, Integer> pageSizeAndOffset(int pageNumber, int pageSize) {
    int offset = (pageNumber - 1) * pageSize;
    Map<String, Integer> resultMap = new HashMap<>();
    resultMap.put("pageSize", pageSize);
    resultMap.put("offset", offset);
    return resultMap;
  }

  @Override
  public MainPagePaginationResponse getNewProducts(int page, int pageSize) {
    log.info("Getting all the new products!");
    return getProductsByQuery(getSql(), page, pageSize);
  }

  private String getSql() {
    return """
                SELECT DISTINCT p.id                            AS product_id,
                                sp.id                           AS sub_product_id,
                                b.name                          AS name,
                                p.name                          AS prodName,
                                sp.price                        AS price,
                                sp.quantity                     AS quantity,
                                sp.code_color                   AS color,
                                d.sale                          AS discount,
                                cat.title                       AS catTitle,
                                sc.title                        AS subCatTitle,
                                p.created_at                    AS createdAt,
                                sp.rating                       AS rating,
                                COALESCE(
                                        (SELECT spi.images
                                         FROM sub_product_images spi
                                         WHERE spi.sub_product_id = sp.id
                                         LIMIT 1), ' ')         AS image,
                                COALESCE(rev.countOfReviews, 0) AS countOfReviews
                FROM sub_products sp
                         LEFT JOIN products p ON p.id = sp.product_id
                         LEFT JOIN brands b ON b.id = p.brand_id
                         LEFT JOIN sub_product_images spi ON sp.id = spi.sub_product_id
                         LEFT JOIN discounts d ON sp.id = d.sub_product_id
                         LEFT JOIN categories cat ON cat.id = p.category_id
                         LEFT JOIN sub_categories sc ON sc.id = p.sub_category_id
                         LEFT JOIN reviews r ON sp.id = r.sub_product_id
                         LEFT JOIN (SELECT sub_product_id, COUNT(id) AS countOfReviews
                                    FROM reviews
                                    GROUP BY sub_product_id) rev ON sp.id = rev.sub_product_id
                WHERE created_at BETWEEN (CURRENT_DATE - INTERVAL '30 Day') AND CURRENT_DATE
                ORDER BY sp.id
                LIMIT ? OFFSET ?
                """;
  }

  @Override
  public MainPagePaginationResponse getRecommendedProducts(int page, int pageSize) {
    String sql = """
                SELECT DISTINCT p.id                            AS product_id,
                                sp.id                           AS sub_product_id,
                                b.name                          AS name,
                                p.name                          AS prodName,
                                sp.price                        AS price,
                                sp.quantity                     AS quantity,
                                sp.code_color                   AS color,
                                d.sale                          AS discount,
                                cat.title                       AS catTitle,
                                sc.title                        AS subCatTitle,
                                p.created_at                    AS createdAt,
                                sp.rating                       AS rating,
                                COALESCE(
                                        (SELECT spi.images
                                         FROM sub_product_images spi
                                         WHERE spi.sub_product_id = sp.id
                                         LIMIT 1), ' ')         AS image,
                                COALESCE(rev.countOfReviews, 0) AS countOfReviews
                FROM sub_products sp
                         LEFT JOIN products p ON p.id = sp.product_id
                         LEFT JOIN brands b ON b.id = p.brand_id
                         LEFT JOIN sub_product_images spi ON sp.id = spi.sub_product_id
                         LEFT JOIN discounts d ON sp.id = d.sub_product_id
                         LEFT JOIN categories cat ON cat.id = p.category_id
                         LEFT JOIN sub_categories sc ON sc.id = p.sub_category_id
                         LEFT JOIN reviews r ON sp.id = r.sub_product_id
                         LEFT JOIN (SELECT sub_product_id, COUNT(id) AS countOfReviews
                                    FROM reviews
                                    GROUP BY sub_product_id) rev ON sp.id = rev.sub_product_id
                WHERE sp.rating > 4.0
                ORDER BY sp.id
                LIMIT ? OFFSET ?
                """;
    return getProductsByQuery(sql, page, pageSize);
  }


  @Override
  public MainPagePaginationResponse getAllDiscountProducts(int page, int pageSize) {
    String sql = """
                SELECT p.id                            AS product_id,
                       sp.id                           AS sub_product_id,
                       b.name                          AS name,
                       p.name                          AS prodName,
                       sp.price                        AS price,
                       sp.quantity                     AS quantity,
                       sp.code_color                   AS color,
                       d.sale                          AS discount,
                       cat.title                       AS catTitle,
                       sc.title                        AS subCatTitle,
                       p.created_at                    AS createdAt,
                       sp.rating                       AS rating,
                       COALESCE((SELECT spi.images
                                 FROM sub_product_images spi
                                 WHERE spi.sub_product_id = sp.id
                                 LIMIT 1), ' ')        AS image,
                       COALESCE(rev.countOfReviews, 0) AS countOfReviews
                FROM sub_products sp
                        LEFT JOIN products p ON p.id = sp.product_id
                        LEFT JOIN brands b ON b.id = p.brand_id
                        LEFT JOIN sub_product_images spi ON sp.id = spi.sub_product_id
                        LEFT JOIN discounts d ON sp.id = d.sub_product_id
                        LEFT JOIN categories cat ON cat.id = p.category_id
                        LEFT JOIN sub_categories sc ON sc.id = p.sub_category_id
                        LEFT JOIN reviews r ON sp.id = r.sub_product_id
                         LEFT JOIN (SELECT sub_product_id, COUNT(id) AS countOfReviews
                                    FROM reviews
                                    GROUP BY sub_product_id) rev ON sp.id = rev.sub_product_id
                WHERE d.sale > 0
                GROUP BY p.id, sp.id, b.name, p.name, sp.price, sp.quantity, sp.code_color, d.sale, cat.title, sc.title, p.created_at,
                         sp.rating, image, countOfReviews
                ORDER BY sp.id
                LIMIT ? OFFSET ?
                """;
    return getProductsByQuery(sql, page, pageSize);
  }

  private MainPagePaginationResponse getProductsByQuery(String sql, int page, int pageSize) {

    List<SubProductMainPageResponse> products = jdbcTemplate.query(
        sql,
        (resultSet, i) -> SubProductMainPageResponse.builder()
            .productId(resultSet.getLong("product_id"))
            .subProductId(resultSet.getLong("sub_product_id"))
            .name(resultSet.getString("name"))
            .prodName(resultSet.getString("prodName"))
            .price(resultSet.getBigDecimal("price"))
            .quantity(resultSet.getInt("quantity"))
            .color(resultSet.getString("color"))
            .discount(resultSet.getInt("discount"))
            .catTitle(resultSet.getString("catTitle"))
            .subCatTitle(resultSet.getString("subCatTitle"))
            .createdAt(resultSet.getDate("createdAt").toLocalDate())
            .rating(resultSet.getDouble("rating"))
            .image(resultSet.getString("image"))
            .countOfReviews(resultSet.getInt("countOfReviews"))
            .build(), pageSize,
        pageSizeAndOffset(page, pageSize).get("offset"));

    List<Long> favorites = utilitiesService.getFavorites();

    for (SubProductMainPageResponse s : products) {
      s.setFavorite(favorites.contains(s.getSubProductId()));
    }

    List<Long> comparisons = utilitiesService.getComparison();

    for (SubProductMainPageResponse s : products) {
      s.setComparison(comparisons.contains(s.getSubProductId()));
    }

    List<Long> subProductIdsInBasket = utilitiesService.getBasket();

    for (SubProductMainPageResponse s : products) {
      s.setInBasket(subProductIdsInBasket.contains(s.getSubProductId()));
    }

    return MainPagePaginationResponse.builder()
        .subProductMainPageResponses(products)
        .page(page)
        .pageSize(pageSize)
        .build();
  }
}