package com.example.hostal_management_b.controller;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/qr")
public class QRCodeController {

//    @PostMapping("/uploadImage")
//    public String processImageWithQRCode(@RequestParam("image") MultipartFile image) {
//        try {
//            String qrCodeData = extractQRCodeData(image);
//
//            if (qrCodeData != null) {
//                System.out.println("QR Code Data: " + qrCodeData);
//                // Perform your business logic based on the extracted data (e.g., reporting issues).
//                return qrCodeData;
//            } else {
//                return "QR code not found or could not be decoded.";
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "Error processing the image.";
//        }
//    }
//
//    private String extractQRCodeData(MultipartFile image) throws IOException {
//        try {
//            BufferedImage bufferedImage = ImageIO.read(image.getInputStream());
//            LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
//            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
//
//            Map<DecodeHintType, Object> hints = new HashMap<>();
//            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//
//            Result result = new MultiFormatReader().decode(binaryBitmap, hints);
//
//            if (result != null) {
//                return result.getText();
//            } else {
//                return null;
//            }
//        } catch (NotFoundException e) {
//            // QR code not found
//            return null;
//        }
//    }
}
