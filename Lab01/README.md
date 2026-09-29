# Lab 01 - Các Layout cơ bản

---
## 1. FrameLayout
- **Mục đích:** Dùng để hiển thị một nội dung đơn lẻ hoặc xếp chồng các View lên nhau.
- **Cơ chế:** Mặc định các view con nằm ở góc trên bên trái. Phần tử khai báo **sau** trong file XML sẽ **nằm đè lên** phần tử khai báo **trước**.

---
## 2. LinearLayout
- **Mục đích:** Sắp xếp các view con theo một hàng duy nhất (ngang hoặc dọc).
- **Thuộc tính quan trọng:**
    - `android:orientation`: Chiều sắp xếp (`vertical` - dọc, `horizontal` - ngang).
    - `android:layout_weight`: Trọng số phân chia tỉ lệ kích thước (chiều rộng/chiều cao) giữa các view con so với không gian còn lại.
- **Lưu ý:** Có thể lồng các `LinearLayout` bên trong nhau để tạo bố cục lưới/cột phức tạp hơn.

---
## 3. TableLayout
- **Mục đích:** Bố cục giao diện theo cấu trúc hàng và cột giống bảng tính.
- **Cơ chế:**
    - Kết hợp thẻ `<TableRow>` bên trong: mỗi thẻ `<TableRow>` tạo thành một hàng.
    - Các phần tử con nằm trong `<TableRow>` đóng vai trò là từng ô (cột).
    - Thuộc tính thường dùng: `android:stretchColumns` để giãn nở cột lấp đầy chiều rộng.

---
## 4. RelativeLayout
- **Mục đích:** Định vị vị trí View con dựa trên **mối quan hệ tương đối** với View cha hoặc với các View lân cận.
- **Nhóm thuộc tính căn theo View khác (tham chiếu qua `@id/...`):**
    - **Vị trí tương quan:** `android:layout_below`, `android:layout_above`, `android:layout_toLeftOf`, `android:layout_toRightOf`.
    - **Gióng hàng (align):** `android:layout_alignTop`, `android:layout_alignBottom`, `android:layout_alignLeft`, `android:layout_alignRight`, `android:layout_alignBaseline`.
- **Nhóm thuộc tính căn theo View cha (giá trị `true` / `false`):**
    - **Căn mép:** `android:alignParentTop`, `android:alignParentBottom`, `android:alignParentLeft`, `android:alignParentRight`.
    - **Căn giữa:** `android:centerHorizontal` (ngang), `android:centerVertical` (dọc), `android:centerInParent` (chính giữa).

---
## 5. ConstraintLayout
- **Mục đích:** Tạo giao diện phức tạp dạng phẳng (Flat Hierarchy), tối ưu hiệu năng và hạn chế tối đa việc lồng nhiều cấp layout.
- **Cơ chế:** Sử dụng hệ thống **ràng buộc (constraints)** giữa các cạnh của View với View cha hoặc với View khác.
- **Thuộc tính ràng buộc cơ bản:**
    - `layout_constraintTop_toTopOf` / `layout_constraintTop_toBottomOf`
    - `layout_constraintBottom_toTopOf` / `layout_constraintBottom_toBottomOf`
    - `layout_constraintStart_toStartOf` / `layout_constraintStart_toEndOf`
    - `layout_constraintEnd_toStartOf` / `layout_constraintEnd_toEndOf`
    - `layout_constraintBaseline_toBaselineOf`

---
## 6. Quy Ước Thuộc Tính Dùng Chung
- **Kích thước View:**
    - `match_parent` (hoặc `fill_parent` cũ): Giãn kích thước bằng đúng View cha chứa nó.
    - `wrap_content`: Co dãn kích thước vừa đủ để ôm trọn nội dung bên trong.
- **Căn lề nội dung:**
    - `android:gravity`: Căn chỉnh vị trí nội dung nằm **bên trong** chính thành phần đó.
    - `android:layout_gravity`: Căn chỉnh vị trí của **chính thành phần đó** bên trong Layout cha.