
# 모바일 프로그래밍 개인과제
>## 첫번째 화면
>[ID,PW EditText], [로그인,회원가입 Button]\
>로그인 버튼 클릭시 DB에서 ID,PW를 가져와 비교, 맞을경우 세번째 화면으로, 아닐경우 Alert 생성 및 표출\
>회원가입 버튼 클릭시 두번째 화면으로 이동

>## 두번째 화면
>[ID,PW,Name,Phone,Address EditText], [ID 확인,회원가입 Button], [약관 동의,동의안함 RadioButton]\
>ID 확인 버튼 클릭시 DB에서 ID검색, 사용가능 여부를 Alert로 확인\
>PW 입력 형식이 맞을경우 PW EditText옆의 TextView를 통해 확인 가능\
>ID가 사용가능하고 PW가 형식에 맞으며 약관에 동의한 상태로 회원가입 버튼을 누르면 가입 완료 및 두번째 화면 종료\
>위 세가지 경우가 맞지 않을경우 Alert 생성 및 표출, 화면 종료되지 않음\
>필수입력사항은 ID,PW 두가지

>## 세번째 화면
>[Switch 1~12], [스위치 반전 Button]\
>12개의 스위치를 모두 On으로 만들 시 Alert출력(성공 메세지)\
>첫 실행화면에서 스위치 반전 버튼을 누를 시 모두 On이 되므로 성공 메세지 출력\
>각각의 스위치는 임의의 한 스위치를 추가로 상태 반전하게 만듬

# 실행 환경
Android Studio 3.3.2\
JAVA 11.0.4 2019-07-16\
Windows 10 Pro 64bit\
Pixel 2 XL API 29
