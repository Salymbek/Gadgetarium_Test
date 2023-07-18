package peaksoft.house.gadgetariumb9.service.impl;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.house.gadgetariumb9.dto.request.ProductRequest;
import peaksoft.house.gadgetariumb9.dto.response.SimpleResponse;
import peaksoft.house.gadgetariumb9.entities.Brand;
import peaksoft.house.gadgetariumb9.entities.Category;
import peaksoft.house.gadgetariumb9.entities.Laptop;
import peaksoft.house.gadgetariumb9.entities.Phone;
import peaksoft.house.gadgetariumb9.entities.Product;
import peaksoft.house.gadgetariumb9.entities.SmartWatch;
import peaksoft.house.gadgetariumb9.entities.SubProduct;
import peaksoft.house.gadgetariumb9.entities.Subcategory;
import peaksoft.house.gadgetariumb9.exception.NotFoundException;
import peaksoft.house.gadgetariumb9.repository.BrandRepository;
import peaksoft.house.gadgetariumb9.repository.CategoryRepository;
import peaksoft.house.gadgetariumb9.repository.ProductRepository;
import peaksoft.house.gadgetariumb9.repository.SubcategoryRepository;
import peaksoft.house.gadgetariumb9.service.SubProductService;

@Service
@AllArgsConstructor
public class SubProductServiceImpl implements SubProductService {

  private final ProductRepository productRepository;

  private final BrandRepository brandRepository;

  private final SubcategoryRepository subCategoryRepository;

  private final CategoryRepository categoryRepository;

  @Override
  public SimpleResponse saveSubProduct(ProductRequest productRequest) {

    Subcategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
        .orElseThrow(() -> new NotFoundException(
            "SubCategory with id: " + productRequest.getSubCategoryId() + "is not found"));

    Brand brand = brandRepository.findById(productRequest.getBrandId())
        .orElseThrow(() -> new NotFoundException(
            "Brand with id: " + productRequest.getBrandId() + "is not found"));

    Category category = categoryRepository.findById(productRequest.getCategoryId())
        .orElseThrow(() -> new NotFoundException(
            "Category with id: " + productRequest.getCategoryId() + "is not found"));
    Product product = Product.builder()
        .subCategory(subCategory)
        .brand(brand)
        .category(category)
        .name(productRequest.getName())
        .dataOfIssue(ZonedDateTime.now())
        .createdAt(ZonedDateTime.now())
        .guarantee(productRequest.getGuarantee())
        .build();

    List<SubProduct> subProducts = productRequest.getSubProductRequests().stream()
        .map(subProductRequest -> {
          SubProduct subProduct = new SubProduct();
          subProduct.setCodeColor(subProductRequest.getCodeColor());
          subProduct.setRom(subProductRequest.getRom());
          subProduct.setRam(subProductRequest.getRam());
          subProduct.setScreenResolution(subProductRequest.getScreenResolution());
          subProduct.setAdditionalFeatures(subProductRequest.getAdditionalFeatures());
          subProduct.setQuantity(subProductRequest.getQuantity());
          subProduct.setImages(subProductRequest.getImages());
          subProduct.setPrice(subProductRequest.getPrice());

          switch (subCategory.getTitle()) {
            case "Ноутбуки и планшеты" -> {
              Laptop laptop = new Laptop();
              laptop.setProcessor(subProductRequest.getProcessor());
              laptop.setPurpose(subProductRequest.getPurpose());
              laptop.setVideoMemory(subProductRequest.getVideoMemory());
              laptop.setScreenSize(subProductRequest.getScreenSize());
              subProduct.setLaptop(laptop);
            }
            case "Смартфоны" -> {
              Phone phone = new Phone();
              phone.setSim(subProductRequest.getSim());
              phone.setDiagonalScreen(subProductRequest.getDiagonalScreen());
              phone.setBatteryCapacity(subProductRequest.getBatteryCapacity());
              subProduct.setPhone(phone);
            }
            case "Смарт-часы и браслеты" -> {
              SmartWatch smartWatch = new SmartWatch();
              smartWatch.setAnInterface(subProductRequest.getAnInterface());
              smartWatch.setHUllShape(subProductRequest.getHullShape());
              smartWatch.setMaterialBracelet(subProductRequest.getMaterialBracelet());
              smartWatch.setHousingMaterial(subProductRequest.getHousingMaterial());
              smartWatch.setGender(subProductRequest.getGender());
              smartWatch.setWaterproof(subProductRequest.isWaterproof());
              smartWatch.setDisplayDiscount(subProductRequest.getDisplayDiscount());
              subProduct.setSmartWatch(smartWatch);
            }
          }
          return subProduct;
        })
        .toList();

    product.setSubProducts(subProducts);

    productRepository.save(product);

    return SimpleResponse.builder()
        .status(HttpStatus.OK)
        .message("successfully added")
        .build();
  }

//  @Override
//  public SimpleResponse saveProductPart2(ProductRequest productRequest,
//      SubProductRequest subProductRequest) {
//
//    Subcategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
//        .orElseThrow(() -> new NotFoundException("SubCategory with id: " + productRequest.getSubCategoryId() + "is not found"));
//
//    Brand brand = brandRepository.findById(productRequest.getBrandId())
//        .orElseThrow(() -> new NotFoundException("Brand with id: " + productRequest.getBrandId() + "is not found"));
//
//
//    Category category = categoryRepository.findById(productRequest.getCategoryId())
//        .orElseThrow(() -> new NotFoundException("Category with id: " + productRequest.getCategoryId() + "is not found"));
//
//    Product product = new Product();
//    product.setCategory(category);
//    product.setSubCategory(subCategory);
//    product.setBrand(brand);
//    product.setName(productRequest.getName());
//    product.setGuarantee(productRequest.getGuarantee());
//    product.setDataOfIssue(productRequest.getDateOfIssue());
//
//    SubProduct subProduct = new SubProduct();
//    subProduct.setCodeColor(subProductRequest.getCodeColor());
//    subProduct.setRom(subProductRequest.getRom());
//    subProduct.setRam(subProductRequest.getRam());
//    subProduct.setScreenResolution(subProductRequest.getScreenResolution());
//    subProduct.setAdditionalFeatures(subProductRequest.getAdditionalFeatures());
//    subProduct.setQuantity(subProductRequest.getQuantity());
//    subProduct.setImages(subProductRequest.getImages());
//    subProduct.setPrice(subProductRequest.getPrice());
//
//    switch (subCategory.getTitle()) {
//      case "Ноутбуки и планшеты" -> {
//        Laptop laptop = new Laptop();
//        laptop.setProcessor(subProductRequest.getProcessor());
//        laptop.setPurpose(subProductRequest.getPurpose());
//        laptop.setVideoMemory(subProductRequest.getVideoMemory());
//        laptop.setScreenSize(subProductRequest.getScreenSize());
//        subProduct.setLaptop(laptop);
//      }
//      case "Смартфоны" -> {
//        Phone phone = new Phone();
//        phone.setSim(subProductRequest.getSim());
//        phone.setDiagonalScreen(subProductRequest.getDiagonalScreen());
//        phone.setBatteryCapacity(subProductRequest.getBatteryCapacity());
//        subProduct.setPhone(phone);
//      }
//      case "Смарт-часы и браслеты" -> {
//        SmartWatch smartWatch = new SmartWatch();
//        smartWatch.setAnInterface(subProductRequest.getAnInterface());
//        smartWatch.setHUllShape(subProductRequest.getHullShape());
//        smartWatch.setMaterialBracelet(subProductRequest.getMaterialBracelet());
//        smartWatch.setHousingMaterial(subProductRequest.getHousingMaterial());
//        smartWatch.setGender(subProductRequest.getGender());
//        smartWatch.setWaterproof(subProductRequest.isWaterproof());
//        smartWatch.setDisplayDiscount(subProductRequest.getDisplayDiscount());
//        subProduct.setSmartWatch(smartWatch);
//      }
//    }
//
//    productRepository.save(product);
//
//    return SimpleResponse.builder()
//        .status(HttpStatus.OK)
//        .message("successfully added")
//        .build();
//  }
//

//  @Override
//  public SimpleResponse saveProductPart3(ProductRequest productRequest) {
//
//    Subcategory subCategory = subCategoryRepository.findById(productRequest.getSubCategoryId())
//        .orElseThrow(() -> new NotFoundException("SubCategory with id: " + productRequest.getSubCategoryId() + "is not found"));
//
//    Brand brand = brandRepository.findById(productRequest.getBrandId())
//        .orElseThrow(() -> new NotFoundException("Brand with id: " + productRequest.getBrandId() + "is not found"));
//
//
//    Category category = categoryRepository.findById(productRequest.getCategoryId())
//        .orElseThrow(() -> new NotFoundException("Category with id: " + productRequest.getCategoryId() + "is not found"));
//
//    Product product = new Product();
//    product.setCategory(category);
//    product.setSubCategory(subCategory);
//    product.setBrand(brand);
//    product.setName(productRequest.getName());
//    product.setGuarantee(productRequest.getGuarantee());
//    product.setDataOfIssue(productRequest.getDateOfIssue());
//
//
//    SubProduct subProduct = new SubProduct();
//    subProduct.setCodeColor(subProductRequest.getCodeColor());
//    subProduct.setRom(subProductRequest.getRom());
//    subProduct.setRam(subProductRequest.getRam());
//    subProduct.setScreenResolution(subProductRequest.getScreenResolution());
//    subProduct.setAdditionalFeatures(subProductRequest.getAdditionalFeatures());
//    subProduct.setQuantity(subProductRequest.getQuantity());
//    subProduct.setImages(subProductRequest.getImages());
//    subProduct.setPrice(subProductRequest.getPrice());
//
//    switch (subCategory.getTitle()) {
//      case "Ноутбуки и планшеты" -> {
//        Laptop laptop = new Laptop();
//        laptop.setProcessor(subProductRequest.getProcessor());
//        laptop.setPurpose(subProductRequest.getPurpose());
//        laptop.setVideoMemory(subProductRequest.getVideoMemory());
//        laptop.setScreenSize(subProductRequest.getScreenSize());
//        subProduct.setLaptop(laptop);
//      }
//      case "Смартфоны" -> {
//        Phone phone = new Phone();
//        phone.setSim(subProductRequest.getSim());
//        phone.setDiagonalScreen(subProductRequest.getDiagonalScreen());
//        phone.setBatteryCapacity(subProductRequest.getBatteryCapacity());
//        subProduct.setPhone(phone);
//      }
//      case "Смарт-часы и браслеты" -> {
//        SmartWatch smartWatch = new SmartWatch();
//        smartWatch.setAnInterface(subProductRequest.getAnInterface());
//        smartWatch.setHUllShape(subProductRequest.getHullShape());
//        smartWatch.setMaterialBracelet(subProductRequest.getMaterialBracelet());
//        smartWatch.setHousingMaterial(subProductRequest.getHousingMaterial());
//        smartWatch.setGender(subProductRequest.getGender());
//        smartWatch.setWaterproof(subProductRequest.isWaterproof());
//        smartWatch.setDisplayDiscount(subProductRequest.getDisplayDiscount());
//        subProduct.setSmartWatch(smartWatch);
//      }
//    }
//
//    productRepository.save(product);
//
//    return SimpleResponse.builder()
//        .status(HttpStatus.OK)
//        .message("successfully added")
//        .build();
//  }
}
