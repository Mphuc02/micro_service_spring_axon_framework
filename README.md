# Thành viên.
- Đinh Minh Phúc - B20DCCN503
- Phan Tuấn Thạch - B20DCCN635
- Bùi Văn Trung - B20DCCN695
# Quiz Lab: Hệ thống cho phép tạo, thực hiện gói các câu hỏi trắc nghiệm

## Case study
- Trang web trắc nghiệm trực tuyến với mục tiêu phục vụ mọi đối tượng người dùng, từ học sinh, sinh viên đến những người muốn kiểm tra kiến thức hoặc thử thách bản thân. Trang web cung cấp một phạm vi đa dạng của các chủ đề trắc nghiệm để đáp ứng nhu cầu học tập và giải trí của mọi người

## Các kịch bản
- Đăng ký tài khoản
- Đăng nhập hệ thống
- Người dùng tạo quiz
- Người dùng tham gia làm bài quiz

## Phân tích các kịch bản
### Kịch bản 1: Người dùng đăng ký tài khoản
#### Bước 1: Các hành động chi tiết
- Truy cập vào trang đăng ký
- Nhập các thông tin cần thiết
- Xác minh tính hợp lệ của thông tin
- Nếu thông tin không hợp lệ, yêu cầu nhập lại

#### Bước 2: Lọc ra các hoạt động không phù hợp
-Truy cập trang đăng ký<br>
**- Nhập các thông tin cần thiết**<br>
**- Xác minh tính hợp lệ của thông tin**<br>
**- Nếu thông tin không hợp lệ, yêu cầu nhập lại**<br>

#### Bước 3: Xác định các thực thể dịch vụ
**-Xác minh tính hợp lệ của thông tin**<br>
**-Nếu thông tin không hợp lệ, yêu cầu nhập lại**<br>

---> Các thực thể dịch vụ:
- Dịch vụ Tài khoản:
  * Tạo mới tài khoản

#### Bước 4: Logic cụ thể của Quy trình
**- Xác minh tính hợp lệ của thông tin**<br>
**- Nếu thông tin không hợp lệ, yêu cầu nhập lại**<br>
- Xác minh tính hợp lệ của thông tin mà gửi dùng đăng ký
- Nếu không hợp lệ, trả về lỗi chứa các thông tin không hợp lệ và mô tả của lỗi
---> Các hành động trên nằm trong logic dịch vụ
  - Dịch vụ Tài khoản:
    * Xác minh tính hợp lệ của thông tin
    * Nếu thông tin không hợp lệ, trả về thông báo lỗi và yêu cầu nhập lại

#### Bước 5: Xác định các nguồn
- /user/registration/: Là nguồn chứa thông tin cho việc đăng ký tài khoản

#### Bước 6: Liên kết khả năng dịch vụ với nguồn và phương pháp
- Dịch vụ Create User
* Dịch vụ này sẽ gửi yêu cầu tạo mới tài khoản bằng phương pháp Post tới nguồn /user/registration/ trong hệ thống

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
- Dịch vụ:  Tạo tài khoản

#### Bước 9: Phân tích yêu cầu xử lý
- Các hành động xác nhận:
  * Gửi thông báo thông tin tài khoản không hợp lệ<br>
    => Ko cần thêm utility-centric functions <br>
    => Có thêm 1 utility service
#### Bước 10: Xác định ứng viên dịch vụ tiện ích
- Notification với hành động: Thông báo thông tin tài khoản không hợp lệ

#### Bước 11: Xác định ứng viên MicroService
- User Service

#### Bước 12: Áp dụng hướng dịch vụ


#### Bước 13: Áp dụng các ứng viên tổ hợp dịch vụ
- Ứng viên tổ hợp dịch vụ Create User đã được mở rộng so với bước 8, sau khi ở bước 9 bước 10 chúng ta xác định thêm được dịch vụ tiện ích là Notification
- Thêm tầng Utility Service Notification

#### Bước 14: Kiểm tra tên resource và phân nhóm khả năng dịch vụ
- Mô hình Rest Service hiện tại
  * Tầng Task service: Create User Service
  * Tầng Entity service: User
  * Tầng Utility Service: Notification

| Checklist  | Kết quả   |
|---|---|
|Tên resource   | Hợp lệ |
|Gom nhóm   |  Hợp lệ   |
|Dư thừa   |  Không |

### Kịch bản 2: Đăng nhập hệ thống
#### Bước 1: Các hành động chi tiết
- Khởi tạo yêu cầu truy cập trang đăng nhập
- Nhập tài khoản và mật khẩu
- Xác minh thông tin đăng nhập của người dùng
- Nếu thông tin không chính xác, thông báo lỗi

#### Bước 2: Lọc ra các hành động không phù hợp
-Khởi tạo yêu cầu truy cập trang đăng nhập
-Nhập tài khoản và mật khẩu<br>
**- Xác minh thông tin đăng nhập của người dùng**<br>
**- Nếu thông tin không chính xác, thông báo lỗi** 

#### Bước 3: Xác định các thực thể dịch vụ
-Khởi tạo yêu cầu truy cập trang đăng nhập
-Nhập tài khoản hoặc mật khẩu<br>
**- Xác minh thông tin đăng nhập của người dùng**
* Kiểm tra độ chính xác về thông tin mà người dùng gửi lên
**- Nếu thông tin không chính xác, thông báo lỗi**<br>
* Trả về thông báo lỗi cho người dùng

---> Các hành động bất khả tri được phân loại như các ứng viên năng lực dịch vụ sơ bộ và được nhóm vào các service thành phần tương ứng như sau:
- Dịch vu người dùng:
  * Kiểm tra thông tin đăng nhập

#### Bước 4: Logic cụ thể của quy trình
**- Xác minh thông tin đăng nhập của người dùng**<br>
* Tìm kiếm dưới cơ sở dữ liệu thông tin có tài khoản dựa trên tên đăng nhập người dùng nhập vào
* Tiền hành mã hóa mật khẩu người dùng gửi lên rồi so sánh với mật khẩu đã được mã hóa dưới cơ sở dữ liệu

---> Logic của dịch vụ Người dùng:
- Kiểm tra thông tin đăng nhập
- Trả về thông báo lỗi

#### Bước 5:  Xác định các nguồn
- /user/login/: Là nguồn cho phép người dùng tiến hành đăng nhập vào hệ thống

#### Bước 6: Liên kết khả năng dịch vụ với nguồn và phương pháp
- Service Người dùng:
  * Dịch vụ này cho phép thông tin đăng nhập theo phương thức POST tới nguồn /user/login

#### Bước 7: Áp dụng hướng dịch vụ
- Bảo mật và quản lý lỗi:
	* Áp dụng các biện pháp bảo mật như xác thực và ủy quyền để bảo vệ dữ liệu.
	* Thiết kế các dịch vụ để có khả năng xử lý các trường hợp lỗi một cách an toàn và thông báo cho người dùng.

#### Bước 8: Xác định ứng viên tổ hợp dịch vụ
- Dịch vụ: Đăng nhập hệ thống

#### Bước 9: Phân tích yêu cầu xử lý
- Gửi thông báo đăng nhập không thành công
- Gửi thông báo đăng nhập thành công

#### Bước 10: Xác định  utility service candidates (và các resources, method liên quan)
- Utility service: Notification với 2 hành động
  * Thông báo đăng nhập thành công
  * Thông báo đăng nhập không thành công

#### Bước 11: Xác định ứng viên Micro service:
- Verify User Service

#### Bước 12: Áp dụng hướng dịch vụ


#### Bước 13: 
- Ứng viên tổ hợp dịch vụ User đã được mở rộng so với bước 8, sau khi ở bước 9 bước 10 chúng ta xác định thêm được dịch vụ tiện ích là Notification
---> Thêm tầng Utility Service Notification

#### Bước 14:
- Mô hình Rest service hiện tại:
  * Tầng Task service: User service
  * Tầng Entity Service: User
  * Tầng Utiility service: Notification
 
| Checklist  | Kết quả   |
|---|---|
|Tên resource   | Hợp lệ |
|Gom nhóm   |  Hợp lệ   |
|Dư thừa   |  Không |

### Kịch bản 3: Người dùng tạo quizz
#### Bước 1: Các hành động chi tiết
- Khởi tạo 1 bài quiz
- Nhập tên và mô tả cho bài quiz
- Khởi tạo các câu hỏi
- Nhập nội dung câu hỏi
- Nhập các câu trả lời cho câu hỏi
- Chỉ định đáp án đúng cho câu hỏi
- Xác minh tính hợp lệ của câu hỏi
- Nếu Câu hỏi không hợp lệ, khởi tạo thông báo lỗi yêu cầu nhập lại
- Xác minh lỗi thủ công và tiến hành nhập lại
- Xác minh tính hợp lệ của bài kiểm tra sau khi hoàn thành thêm câu hỏi
- Nếu Bài kiểm tra không hợp lệ, khởi tạo thông báo lỗi
- Xác minh lỗi thủ công
- Xác minh Thông báo thành công thủ công

#### Bước 2: Lọc ra các hành động không phù hợp
**- Khởi tạo 1 bài quiz**<br>
**- Nhập tên và mô tả cho bài quiz**<br>
**- Nhập nội dung các câu hỏi**<br>
**- Nhập các câu trả lời cho câu hỏi**<br>
**- Chỉ định đáp án đúng cho câu hỏi**<br>
**- Xác minh lỗi thủ công và tiến hành nhập lại**<br>
**- Xác minh lỗi thủ công**<br>
**- Xác minh Thông báo thành công thủ công**<br>

#### Bước 3: Xác định các thực thể dịch vụ
**- Xác minh lỗi thủ công và tiến hành nhập lại**<br>
**- Xác minh lỗi thủ công**<br>
**- Xác minh Thông báo thành công thủ công**<br>

---> Các thực thể dịch vụ:
- Dịch vụ Bài Quiz:
  + Tạo mới 1 bài Quiz
 
#### Bước 4: Logic cụ thể của quy trình
**- Khởi tạo bài quiz => Ứng viên khả năng dịch vụ**
**- Nhập tên và mô tả cho bài quiz => Ứng viên khả năng dịch vụ**<br>
**- Nhập nội dung các câu hỏi => Ứng viên khả năng dịch vụ**<br>
**- Nhập các câu trả lời cho câu hỏi => Ứng viên khả năng dịch vụ**<br>
**- Chỉ định đáp án đúng cho câu hỏi => Ứng viên khả năng dịch vụ**<br>

#### Bước 5: Xác định các nguồn
- /quiz/: Là nguồn cho phép thêm mới 1 bài quiz

#### Bước 6: Liên kết khả năng dịch vụ với nguồn và phương pháp
- Service Create quiz:
  * Dịch vụ này sẽ gửi yêu cầu tạo bài quiz mới bằng phương pháp POST tới nguồn /quiz/ trong hệ thống. Các thông tin của bài quiz sẽ được gửi kèm theo
 
#### Bước 7: Áp dụng hướng dịch vụ
- Khả năng mở rộng và linh hoạt:
	* Hệ thống cần được thiết kế để có khả năng mở rộng dễ dàng khi có nhu cầu.
	* Sử dụng kiến trúc dựa trên dịch vụ giúp dễ dàng thêm mới các dịch vụ và mở rộng hệ thống mà không ảnh hưởng đến các dịch vụ hiện có.
- Hiệu suất và độ tin cậy:
	* Đảm bảo rằng các dịch vụ được thiết kế để hoạt động hiệu quả và đáng tin cậy trong môi trường triển khai.
	* Kiểm tra và tối ưu hóa hiệu suất của các dịch vụ để đảm bảo khả năng xử lý các yêu cầu một cách nhanh chóng và hiệu quả.
- Bảo mật và quản lý lỗi:
	* Bảo vệ dữ liệu và đảm bảo tính bảo mật của hệ thống bằng cách triển khai các biện pháp bảo mật thích hợp.
	* Thiết kế các dịch vụ để có khả năng xử lý các trường hợp lỗi một cách an toàn và đảm bảo rằng hệ thống không bị gián đoạn do các vấn đề kỹ thuật.

#### Bước 8: Xác định ứng viên tổ hợp dịch vụ
- Dịch vụ: tạo bài quiz

#### Bước 9: Phân tích yêu cầu xử lý
- Các hành động xác nhận:
  * Gửi thông báo câu hỏi không hợp lệ
    => Ko cần thêm utility-centric functions 
    => Có thêm 1 utility service
    => Cần xác định người dùng đã đăng nhập thì mới có thể gửi yêu cầu

#### Bước 10: Xác định  utility service candidates (và các resources, method liên quan)
- Utility Service: Notification với 2 hành động: 
* Thông báo câu hỏi không hợp lệ

#### Bước 11: Xác định ứng viên Microservice: 
- Verify User Service
- 
#### Bước 12: Áp dụng hướng dịch vụ

#### Bước 13: Kiểm tra các ứng viên tổ hợp dịch vụ
- Ứng viên tổ hợp dịch vụ Create Quiz đã được mở rộng so với bước 8, sau khi ở bước 9 bước 10 chúng ta xác định thêm được dịch vụ tiện ích là Notification
---> Thêm tầng Utility Service Notification

#### Bước 14: Kiểm tra tên resource và phân nhóm khả năng dịch vụ
- Mô hình Rest service hiện tại:
  * Tầng Tesk service: Create Quiz
  * Tầng Entity service: Quiz, Question
  * Tầng Utility Service: Notification

| Checklist  | Kết quả   |
|---|---|
|Tên resource   | Hợp lệ |
|Gom nhóm   |  Hợp lệ   |
|Dư thừa   |  Không |
