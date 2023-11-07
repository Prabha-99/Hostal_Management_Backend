package com.example.hostal_management_b.service;

import com.example.hostal_management_b.dto.PropertyDto;
import com.example.hostal_management_b.model.Item_QR;
import com.example.hostal_management_b.model.Property;
import com.example.hostal_management_b.model.Room;
import com.example.hostal_management_b.repository.ItemQRRepo;
import com.example.hostal_management_b.repository.PropertyRepo;
import com.example.hostal_management_b.repository.RoomRepo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PropertyService {

    private final PropertyRepo propertyRepo;
    private final RoomRepo roomRepo;
    private final ItemQRRepo itemQRRepo;

    private final JdbcTemplate jdbcTemplate;

    // Specify the directory where QR codes will be saved
    private static final String QR_CODE_DIRECTORY = "F:\\Uni Works\\Level 3\\Sem 2\\ADBMS\\Group_Project\\QR";

    public Property addProperty(Property property) {
        int roomNo = property.getRoom_no();
        Room room = roomRepo.findById(roomNo).orElse(null);

        if (room != null) {
            String sql = "CALL update_no_of_items_after_adding_property()";
            jdbcTemplate.execute(sql);
        }

        Property savedProperty = propertyRepo.save(property);
        String qrCode = generateQRCode(savedProperty.getPropID());

        Item_QR itemQR = new Item_QR();
        itemQR.setProperty(savedProperty);
        itemQR.setQR(qrCode);

        itemQRRepo.save(itemQR);

        return savedProperty;
    }

    private String generateQRCode(String propID) {
        int width = 300;
        int height = 300;

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(propID, BarcodeFormat.QR_CODE, width, height, hints);

            // Specify the file path where the QR code will be saved
            String filePath = QR_CODE_DIRECTORY + "\\" + propID + ".png";
            Path path = Paths.get(filePath);

            // Write the QR code image to the specified file
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

            return filePath; // Return the file path if needed
        } catch (WriterException | IOException e) {
            e.printStackTrace();
            // Handle exception appropriately in your application
            return null;
        }
    }

    public List<Property> getAllItems() {
        return propertyRepo.findAll();
    }

    public long getCountItems(){
        String sql = "SELECT GetPropertyCount()";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }


    public List<PropertyDto> findProperty(int inputRoom) {
        String query = "CALL GetRoomProperties(?)";
        return jdbcTemplate.query(query, new Object[]{inputRoom}, (rs, rowNum) -> {
            PropertyDto room = new PropertyDto();
            room.setPropID(rs.getString("propID"));
            room.setName(rs.getString("name"));
            room.setStatus(rs.getString("status"));
            return room;
            });
            }
}
