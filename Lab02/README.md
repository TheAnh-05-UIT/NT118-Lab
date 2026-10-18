# LAB 02 – LISTVIEW VÀ RECYCLERVIEW

## 1. Mục tiêu kiến thức

Lab 02 giới thiệu cách hiển thị và tương tác với các tập dữ liệu trên giao diện Android thông qua **ListView, GridView, Spinner và RecyclerView**. Nội dung tập trung vào vai trò của nguồn dữ liệu, Adapter, giao diện từng phần tử và cách cập nhật giao diện khi dữ liệu thay đổi. Bài lab cũng vận dụng kiến thức lập trình hướng đối tượng để quản lý danh sách các đối tượng nhân viên hoặc món ăn.

## 2. ListView và nguồn dữ liệu

**ListView** là thành phần giao diện hiển thị dữ liệu dưới dạng danh sách theo chiều dọc. Mỗi dòng tương ứng với một phần tử trong nguồn dữ liệu. ListView có thể sử dụng mảng có sẵn, mảng chuỗi lưu trong tài nguyên XML, `ArrayList<String>` hoặc danh sách các đối tượng.

- **Mảng dữ liệu định sẵn:** Phù hợp với danh sách ít thay đổi, giúp làm quen với việc liên kết nguồn dữ liệu và giao diện.
- **Mảng lưu trong XML:** Tách dữ liệu hiển thị khỏi mã Java, thuận tiện cho quản lý tài nguyên.
- **ArrayList:** Có khả năng thay đổi kích thước linh hoạt, thích hợp khi người dùng thêm hoặc xóa phần tử trong lúc chạy.
- **Sự kiện chọn phần tử:** `OnItemClickListener` nhận vị trí phần tử, cho phép hiển thị nội dung tương ứng; `OnItemLongClickListener` xử lý thao tác nhấn giữ.
- **Trạng thái chọn:** Chế độ `singleChoice` cho phép đánh dấu một phần tử tại một thời điểm; màu hiển thị phụ thuộc vào cấu hình giao diện và trạng thái chọn.

## 3. Adapter và cập nhật dữ liệu

**Adapter** là thành phần trung gian chuyển dữ liệu thành các View để hiển thị trong danh sách. `ArrayAdapter` phù hợp với dữ liệu đơn giản và có thể sử dụng layout dòng được Android cung cấp. Với giao diện phức tạp, cần xây dựng **CustomAdapter** để tự quyết định cách bố trí và trình bày từng phần tử.

Khi nội dung của `ArrayList` thay đổi, giao diện thường được yêu cầu cập nhật bằng `notifyDataSetChanged()`. Việc cập nhật Adapter là bước quan trọng để danh sách hiển thị đúng dữ liệu hiện tại.

## 4. Danh sách đối tượng và lập trình hướng đối tượng

Bài thực hành sử dụng danh sách `ArrayList<Employee>` để lưu nhiều đối tượng thay vì chỉ lưu chuỗi văn bản. Mô hình nhân viên gồm lớp cha `Employee` với thông tin chung và hai lớp con `EmployeeFullTime`, `EmployeePartTime`.

- **Đóng gói:** Dữ liệu được quản lý thông qua thuộc tính và các phương thức truy cập.
- **Kế thừa:** Các loại nhân viên tái sử dụng thuộc tính và hành vi của lớp cha.
- **Đa hình:** Một danh sách có kiểu `Employee` có thể chứa đối tượng của nhiều lớp con.
- **Ghi đè phương thức:** Các lớp con triển khai `tinhLuong()` và `toString()` theo cách riêng.
- **`toString()`:** Quy định nội dung văn bản của đối tượng khi được hiển thị qua Adapter đơn giản.

Trong tài liệu lab, mức lương được minh họa khác nhau cho nhân viên chính thức và thời vụ; đây là giá trị phục vụ bài thực hành, không phải mô hình tính lương tổng quát.

## 5. CustomAdapter cho ListView

**CustomAdapter** cho phép mỗi dòng ListView có nhiều thành phần giao diện thay vì chỉ một dòng chữ. Bài lab sử dụng dòng nhân viên gồm tên, chữ **Staff** hoặc biểu tượng **Manager**.

- **Layout cho từng dòng:** Tệp `item_employee.xml` quy định cấu trúc View của một nhân viên.
- **Phương thức `getView()`:** Lấy dữ liệu tại vị trí cần hiển thị và gán thông tin lên các View tương ứng.
- **`convertView`:** Tái sử dụng View đã tạo, hạn chế việc tạo lại giao diện trong quá trình cuộn.
- **Hiển thị theo điều kiện:** Trạng thái Manager quyết định hiển thị biểu tượng hay chữ Staff.
- **Màu nền xen kẽ:** Vị trí chẵn/lẻ được sử dụng để phân biệt các dòng liên tiếp.

## 6. GridView và Spinner

**GridView** hiển thị phần tử theo dạng lưới nhiều cột. Trong bài lab, danh sách món ăn được bố trí hai cột; mỗi phần tử gồm ảnh đại diện, tên món và biểu tượng khuyến mãi khi cần.

**Spinner** là thành phần lựa chọn một giá trị trong danh sách. Tài liệu yêu cầu Spinner chọn từ bốn thumbnail có sẵn và hiển thị danh sách dưới dạng **dialog**, không phải danh sách xổ xuống thông thường.

- **CustomAdapter cho GridView:** Quy định cách hiển thị từng món ăn, gồm ảnh, tên và trạng thái khuyến mãi.
- **CustomAdapter cho Spinner:** `getDropDownView()` tạo giao diện lựa chọn gồm tên và ảnh; `getView()` trình bày giá trị đã chọn, chỉ gồm tên thumbnail.
- **Enum thumbnail:** Tập hợp những lựa chọn hình ảnh cố định cùng thông tin tên và tài nguyên ảnh.
- **Trạng thái giao diện:** Sau khi thêm món ăn, dữ liệu nhập được đưa về giá trị ban đầu và thông báo kết quả bằng Toast.

## 7. RecyclerView

**RecyclerView** là thành phần hiển thị danh sách dựa trên cơ chế tái sử dụng các View, phù hợp với dữ liệu và giao diện đa dạng. Yêu cầu cuối lab là làm lại bài nhân viên sử dụng CustomAdapter cho ListView bằng RecyclerView.

- **`RecyclerView.Adapter`:** Cầu nối giữa dữ liệu và các ViewHolder.
- **`ViewHolder`:** Lưu tham chiếu đến các View thuộc một dòng để thuận tiện cho việc tái sử dụng.
- **`onCreateViewHolder()`:** Tạo ViewHolder từ layout của một phần tử.
- **`onBindViewHolder()`:** Gán dữ liệu của phần tử tại vị trí tương ứng vào ViewHolder.
- **`getItemCount()`:** Cho biết số phần tử có trong danh sách.
- **`LayoutManager`:** Quy định cách sắp xếp danh sách, chẳng hạn theo chiều dọc hoặc dạng lưới.
- **Cập nhật dữ liệu:** RecyclerView hỗ trợ thông báo thay đổi theo từng phần tử như thêm hoặc xóa, thay vì luôn yêu cầu cập nhật toàn bộ danh sách.

## 8. So sánh ListView và RecyclerView

| Tiêu chí | ListView | RecyclerView |
|---|---|---|
| Vai trò | Hiển thị danh sách | Hiển thị danh sách linh hoạt |
| Adapter | Thường dùng ArrayAdapter hoặc CustomAdapter | Dùng RecyclerView.Adapter |
| Tái sử dụng View | Thông qua `convertView` | Thông qua ViewHolder |
| ViewHolder | Không bắt buộc | Là thành phần của cơ chế Adapter |
| Bố cục | Chủ yếu danh sách dọc | Hỗ trợ nhiều cách bố trí qua LayoutManager |
| Cập nhật | Thường dùng `notifyDataSetChanged()` | Có API thông báo thêm, xóa, thay đổi từng mục |

ListView đơn giản và phù hợp để tìm hiểu cơ chế Adapter. RecyclerView có cấu trúc rõ ràng hơn cho các danh sách cần khả năng tùy biến và cập nhật chi tiết.

## 9. Những kiến thức giao diện bổ sung

- **`VISIBLE`:** View hiển thị và chiếm không gian trên giao diện.
- **`INVISIBLE`:** View bị ẩn nhưng vẫn giữ không gian bố cục.
- **`GONE`:** View bị ẩn và không chiếm không gian bố cục.
- **`centerCrop`:** Giữ tỷ lệ hình ảnh và cắt bớt phần dư để lấp đầy khung.
- **`fitCenter`:** Giữ tỷ lệ hình ảnh, thu phóng để toàn bộ ảnh nằm trong khung.
- **`fitXY`:** Kéo giãn ảnh theo cả hai chiều để lấp đầy khung, có thể làm biến dạng ảnh.
- **`marquee`:** Hiệu ứng chữ chạy khi nội dung dài hơn vùng hiển thị; ngoài ra TextView còn hỗ trợ cắt bớt chữ hoặc xuống dòng.
