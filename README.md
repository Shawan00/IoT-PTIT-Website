# Dự án website IoT
Đây là một website cho hệ thống IoT dùng cho việc hiển thị các thông số nhiệt độ, độ ẩm, ánh sáng và điều khiển bật/tắt 3 thiết bị thông minh.
## Tài liệu về dự án
Bạn có thể xem chi tiết về dự án tại [đây](https://drive.google.com/file/d/11XLGG6zAexkFONi131BrgzNLj7N0UowN/view?usp=drive_link).
## Hướng dẫn chạy
### Bước 1: Chuẩn bị
- Phần cứng thiết bị gồm:
  - 1 Breadboard
  - 1 Module thu phát Wifi ESP8266 NodeMCU
  - 1 DHT11
  - 1 cảm biến ánh sáng LDR
  - 3 đèn LED
- Chi tiết cách nối mạch xem trong tài liệu đi kèm.
- Cài đặt MQTT broker
### Bước 2: Nạp code vào ESP8266
- Sử dụng phần mềm Arduino IDE hoặc PlatformIO trong VS Code để mở file hardware/main.cpp
- Thay đổi tên mạng Wifi và mật khẩu tại dòng 6, 7
- Thay đổi địa chỉ IP của máy tính cá nhân tại dòng 8, 59
- Upload code vào ESP8266
### Bước 3: Tạo cơ sở dữ liệu
Sử dụng hệ quản trị cơ sở dữ liệu MySQL và tạo một cơ sở dữ liệu tên iotptit
Tại file src/main/resources/application.properties thay đổi username và password thành username và password đã cài đặt trong MySQL
### Bước 4: Chạy dự án
Sử dụng một IDE cho java để mở dự án và chạy file src/main/java/com/ptit/ProjectIoT/ProjectIoTApplication.java
Cấp nguồn điện cho ESP8266
Mở http://localhost:8080 để theo dõi website
