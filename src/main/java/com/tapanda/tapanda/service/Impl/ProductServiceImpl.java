package com.tapanda.tapanda.service.Impl;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import com.tapanda.tapanda.enum_.Consultation;
import com.tapanda.tapanda.exception.CategoryNotFoundException;
import com.tapanda.tapanda.exception.ProductNotFoundException;
import com.tapanda.tapanda.exception.SellerNotFoundException;
import com.tapanda.tapanda.model.*;
import com.tapanda.tapanda.repository.CategoryRepository;
import com.tapanda.tapanda.repository.ImageRepository;
import com.tapanda.tapanda.repository.ProductRepository;
import com.tapanda.tapanda.requestDto.*;
import com.tapanda.tapanda.service.*;

import org.springframework.stereotype.Service;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final SellerService sellerService;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final PaidOptionService paidOptionService;
    private final FaqService faqService;
    private final VideoService videoService;
    private final FileStorageService fileStorageService;
    private final ImageRepository imageRepository;

    @Override
    public Optional<Product> registerProduct(@Valid ProductAdminRequestDto request, Long userId,  List<MultipartFile> fileList) {

        Optional<Seller> sellerOptional = sellerService.findByUserId(userId);
        Seller seller = sellerOptional.orElseThrow(() -> new SellerNotFoundException("등록되지 않은 판매자입니다.", null, null));

        Long categoryId = categoryRepository.findIdByCategoryCode(request.getCategoryCode());
        // System.out.println("category ID: " + categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다.", null, null));

        Product product = productRepository.save(request.toEntity(seller, category));

        // 유료옵션 --------------------------------------//
        List<PaidOptionRequestDto> paidOptionList = request.getPaidOptionList();
        List<PaidOption> paidOptionArray = new ArrayList<>();

        if (paidOptionList != null && !paidOptionList.isEmpty()) {
            for (PaidOptionRequestDto paidOptionRequestDto : paidOptionList) {
                // 유료옵션 내용이 있을 때만 유료옵션 등록
                if (paidOptionRequestDto.getDetail() != null && !paidOptionRequestDto.getDetail().isEmpty()) {
                    Optional<PaidOption> savedPaidOption = paidOptionService.registerPaidOption(paidOptionRequestDto, product.getId());
                    savedPaidOption.ifPresent(paidOptionArray::add);
                }
            }
        }

        // FAQ -----------------------------------------//
        List<FaqRequestDto> faqList = request.getFaqList();
        List<Faq> faqArray = new ArrayList<>();

        if (faqList != null && !faqList.isEmpty()) {
            for (FaqRequestDto faqRequestDto : faqList) {
                // FAQ 질문내용이 있을 때만 FAQ 등록
                if (faqRequestDto.getQuestion() != null && !faqRequestDto.getQuestion().isEmpty()) {
                    Optional<Faq> savedFaq = faqService.registerFaq(faqRequestDto, product.getId());
                    savedFaq.ifPresent(faqArray::add);
                }
            }
        }

        // 이미지 ---------------------------------------//
        List<Image> imageList = new ArrayList<>();

        if (fileList != null && !fileList.isEmpty()) {
            for (MultipartFile file : fileList) {
                FileRequestDto fileRequestDto = fileStorageService.productFileUpload(file);
                Image image = fileRequestDto.toEntity();
                image.setProduct(product);
                imageList.add(image);
            }
        }
        imageRepository.saveAll(imageList);

        // 비디오 ---------------------------------------//
        List<VideoRequestDto> videoList = request.getVideoList();
        List<Video> videoArray = new ArrayList<>();

        if (videoList != null && !videoList.isEmpty()) {
            for (VideoRequestDto videoRequestDto : videoList) {
                // 비디오 URL이 있을 때만 비디오 등록
                if (videoRequestDto.getUrl() != null && !videoRequestDto.getUrl().isEmpty()) {
                    Optional<Video> savedVideo = videoService.registerVideo(videoRequestDto, product.getId());
                    savedVideo.ifPresent(videoArray::add);
                }
            }
        }
        return Optional.ofNullable(product);
    }

    @Override
    public Optional<Product> updateProduct(@Valid ProductAdminRequestDto request, Long productId) {
        // TODO Auto-generated method stub
        Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException("해당 서비스를 찾을수 없습니다.", null, null));

        Long categoryId = categoryRepository.findIdByCategoryCode(request.getCategoryCode());
        // System.out.println("category ID: " + categoryId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("해당 카테고리를 찾을 수 없습니다.", null, null));

        product.update(Consultation.valueOf(request.getConsulType()), request.getTitle(), request.getSubtitle(), request.getServiceDescription(),
                request.getServicePrice(), request.getDeliveryTime(), request.getMaxBuy(), request.getPurchaseRequest(),
                request.getEstimateAccept(), request.getEstimateRequired(), request.getEstimateRequest(), request.getDeliveryReceipt(),
                request.getRating(), request.getChatting(), request.getProductConfirm(), category);
        return Optional.ofNullable(productRepository.save(product));
    }
}