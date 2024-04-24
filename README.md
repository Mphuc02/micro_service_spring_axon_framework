# Thành viên.
- Đinh Minh Phúc - B20DCCN503
- Phan Tuấn Thạch - B20DCCN635
- Bùi Văn Trung - B20DCCN695
# Quiz Lab: Hệ thống cho phép tạo, thực hiện gói các câu hỏi trắc nghiệm

# I. Phân tích quy trình nghiệp vụ bài toán

## Case study
- Trang web trắc nghiệm trực tuyến với mục tiêu phục vụ mọi đối tượng người dùng, từ học sinh, sinh viên đến những người muốn kiểm tra kiến thức hoặc thử thách bản thân. Trang web cung cấp một phạm vi đa dạng của các chủ đề trắc nghiệm để đáp ứng nhu cầu học tập và giải trí của mọi người

## Các kịch bản
- Người dùng tạo 1 bài quiz

## Phân tích các kịch bản
- Quy trình nghiệp vụ Người dùng tạo 1 bài quiz
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/675ea295-24f5-4707-b717-84d88ac1200b)


### Kịch bản 1: Người dùng tạo 1 bài quiz
#### Bước 1: Các hành động chi tiết
- Truy cập vào trang tạo quiz
- Nhập tên bài quiz
- Nhập tên các người tham gia
- Bấm nút thêm 1 người tham gia
- Bấm nút đăng bài quiz lên
- Nhập các thông tin cần thiết
- Bấm nút đăng bài quiz
- Lấy thông tin người gửi người gửi quiz
- Kiểm tra tính hợp lệ của bài quiz
- Kiểm tra danh sách người tham gia
- Lưu thông tin bài quiz vào cơ sở dữ liệu
- Hiển thị trang mời tham gia bài quiz gồm có đường dẫn tới trang nhằm chơi bài quiz này và 1 mã qr đại diện cho đường link đó

#### Bước 2: Lọc ra các hoạt động không phù hợp
-Truy cập vào trang tạo quiz<br>
**-Nhập tên bài quiz<br>**
**-Nhập tên các người tham gia<br>**
**-Bấm nút thêm 1 người tham gia<br>**
**-Bấm nút đăng bài quiz lên<br>**
**-Nhập các thông tin cần thiết<br>**
**-Bấm nút đăng bài quiz<br>**
**-Lấy thông tin người gửi người gửi quiz<br>**
**-Kiểm tra tính hợp lệ của bài quiz<br>**
**- Kiểm tra danh sách người tham gia<br>**
**-Lưu thông tin bài quiz vào cơ sở dữ liệu<br>**
- Hiển thị trang mời tham gia bài quiz gồm có đường dẫn tới trang nhằm chơi bài quiz này và 1 mã qr đại diện cho đường link đó


#### Bước 3: Xác định các thực thể dịch vụ
-Truy cập vào trang tạo quiz<br>
-Nhập tên bài quiz
-Nhập tên các người tham gia
-Bấm nút thêm 1 người tham gia
-Bấm nút đăng bài quiz lên
-Nhập các thông tin cần thiết
-Bấm nút đăng bài quiz<br>
**-Kiểm tra tính hợp lệ của bài quiz<br>**
**-Lấy thông tin người gửi người gửi quiz<br>**
**- Kiểm tra danh sách người tham gia<br>**
**-Lưu thông tin bài quiz vào cơ sở dữ liệu<br>**
- Hiển thị trang mời tham gia bài quiz gồm có đường dẫn tới trang nhằm chơi bài quiz này và 1 mã qr đại diện cho đường link đó

Các hành động bất khả tri được phân loại như các ứng viên năng lực dịch vụ sơ bộ và được nhóm vào các service thành phần tương ứng như sau:
- Dịch vụ Xác thực:
  * Hành động Lấy thông tin người gửi quiz được xem như là một năng lực dịch vụ có tên **authenticateUser** và kiểm tra danh sách người tham gia với năng lực dịch vụ **checkUsers** là 1 phần của thực thể dịch vụ có tên Auth<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/d31fee65-bc56-4516-8562-4c72ec35a5b1)

- Dịch vụ Quiz:
  * Lưu thông tin bài quiz vào cơ sở dữ liệu là hành động ứng viên cho thực thể dịch vụ có tên Quiz<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/94012d1d-d18b-45cb-96b0-c87cd0af1d99)

#### Bước 4: Logic cụ thể của Quy trình
**-Kiểm tra tính hợp lệ của bài quiz<br>**
**-Lưu thông tin bài quiz vào cơ sở dữ liệu<br>**

Các hành động sau xảy ra bên trong Task service Create Quiz
- Lấy thông tin người gửi là ai, nếu người dùng không đăng nhập thì kết thúc
- Khởi tạo 1 bài quiz
- Kiểm tra tên của bài quiz, nếu không hợp lệ thì kết thúc
- Đọc file excel để lấy ra danh sách các câu hỏi của bài quiz, nếu file không hợp lệ hoặc câu hỏi không hợp lệ thì kết thúc
- Kiểm tra danh sách người có thể tham dự có ai không tồn tại không. Nếu có thì sẽ kết thúc

![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/4b1c6890-7c8d-4644-93b5-61a9a172befe)
- Hành động khởi tạo 1 bài quiz được dịch thành 1 ứng viên năng lực dịch vụ đơn giản có tên là Start là 1 thành phần của Task service CreateQuiz

#### Bước 5: Xác định các nguồn
**- Việc giao tiếp với các service thuộc thông qua 1 MessageQueue và cần các sự kiện sau:**
<br>-AuthenticationCommonQuery
<br>-CheckUsersExístQuery
<br>-CreateQuizCommand

* Thiết lập một số ánh xạ sơ bộ giữa các nguồn đã xác định và thực thể

| Entity  | Resource   |
|---|---|
| Quiz | CreateQuizCommand |
| Auth	| AuthenticationCommonQuery <br> CheckUserExistQuery|
  
#### Bước 6: Liên kết khả năng dịch vụ với nguồn và phương pháp
**Task service CreateQuiz<br>**
- Nghiệp vụ đầu vào để bắt đầu quy trình nghiệp vụ bài toán<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/7c90c0ab-b813-48bd-bdc6-72d6cf166965)

**Auth**<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/8fc294da-8948-441f-aeb6-47c5286bbab2)

**Quiz**<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/6c91e175-8444-4081-92df-b14bbdac7588)

#### Bước 7: Áp dụng hướng dịch vụ
- Tính tự chủ của dịch vụ:
  * Các dịch vụ cần được thiết kế với tính tự chủ cao, theo nguyên tắc Service Autonomy, để không phụ thuộc quá nhiều vào các hệ thống cũ hoặc ngoại vi.
  * Điều này giúp tối ưu hóa hiệu suất và khả năng mở rộng của hệ thống bằng cách loại bỏ các ràng buộc về phụ thuộc và giảm thiểu sự trễ do giao tiếp với các hệ thống khác.
- Hiệu suất và độ tin cậy:
  * Đảm bảo rằng quy trình đăng ký tài khoản được thiết kế để hoạt động hiệu quả và đáng tin cậy trong mọi tình huống triển khai.
  * Kiểm tra và tối ưu hóa hiệu suất của quy trình để đảm bảo khả năng xử lý các yêu cầu đăng ký một cách nhanh chóng và hiệu quả.
- Bảo mật và quản lý lỗi:
  * Bảo vệ dữ liệu và đảm bảo tính bảo mật của quy trình đăng ký bằng cách triển khai các biện pháp bảo mật cần thiết.
  * Thiết kế quy trình đăng ký để có khả năng xử lý các lỗi một cách an toàn và đảm bảo rằng hệ thống không bị gián đoạn do các vấn đề kỹ thuật.
 
#### Bước 8: Xác định ứng viên tổ hợp dịch vụ
- Dịch vụ: Tạo bài quiz<br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/2f2070e0-e0fa-4c87-b26d-d03eef205f13)

#### Bước 9: Phân tích yêu cầu xử lý
- Các hành động xác nhận:
  + Hệ thống tự động kiểm tra độ hợp lệ của bài quiz nên không cần thêm chức năng utility-centric nào nữa

#### Bước 10: Xác định ứng viên dịch vụ tiện ích
- Nhóm các bước xử lý xoay quanh tiện ích theo ngữ cảnh được xác định trước
Với các ứng viên dịch vụ tiện ích, ngữ cảnh (context) chính là mối quan hệ logic giữa các capability candidates trên các yếu tố:
+ Liên kết với một hệ thống kế thừa cụ thể
+ Liên kết với một hoặc nhiều thành phần giải pháp
+ Nhóm logic theo loại chức năng
Đầu vào chính sẽ là bất kỳ tài nguyên tập trung vào tiện ích nào được xác định trước đó ở Bước 5.
- Bổ sung thêm ứng viên năng lực dịch vụ **Notification** và phương thức **sendNoti** <br>
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/7996258f-483e-4289-b82e-daba5febe635)


#### Bước 11: Xác định ứng viên MicroService
- Như chúng em đã xác định ở bước 9 nên sẽ không có ứng viển microservice

#### Bước 12: Áp dụng hướng dịch vụ
- Bước này giống bước 7, xác định thêm ứng viên dịch vụ **Notification**

#### Bước 13: Áp dụng các ứng viên tổ hợp dịch vụ
- Xem lại các kịch bản ứng viên tổ hợp dịch vụ đã xác định ở Bước 8
![image](https://github.com/jnp2018/midproj-503635695/assets/105010427/e6c252dc-b862-4e5d-80d3-949210f94dee)

#### Bước 14: Kiểm tra tên resource và phân nhóm khả năng dịch vụ
- Mô hình Rest Service hiện tại
  * Tầng Task service: CreateQuiz
  * Tầng Entity service: Quiz, Auth
  * Tầng Utility Service: Notification
    
| Checklist  | Kết quả   |
|---|---|
|Tên resource   | Hợp lệ |
|Gom nhóm   |  Hợp lệ   |
|Dư thừa   |  Không |

## II. Thiết kế theo REST SERVICE



