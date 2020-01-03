Plot -Android

##  🎬 영화관에서의 첫 맛남 , 영화 데이트 매칭 서비스 🍿

 개발기간:  Dec 22, 2019 - Jan 4, 2020

<img width="300" alt="plot" src="https://user-images.githubusercontent.com/57608585/71559878-9d498400-2aa6-11ea-81d1-0f407b7bc3c6.png">





# **Project Environment**

![](https://img.shields.io/badge/Android-29-green) ![](https://img.shields.io/badge/Kotlin-1.3.41-blue)



## Application Service Work flow

![](C:\Users\gh060\AppData\Roaming\Typora\typora-user-images\image-20200103223249437.png)

# Using Library

- recyclerview : 리사이클러뷰를 그리기 위한 라이브러리

  ```kotlin
  implementation 'androidx.recyclerview:recyclerview:1.1.0'
  ```

- glide : Retrofit에서 Gson을 사용하기 위한 라이브러리

  ```kotlin
  implementation 'com.github.bumptech.glide:glide:4.9.0'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.9.0'
  ```

- retrofit :Retrofit을 사용하기 위한 라이브러리

  ```kotlin
  implementation 'com.squareup.retrofit2:retrofit-mock:2.6.2'
  implementation 'com.squareup.retrofit2:retrofit:2.6.2'
  ```

- Gson : Gson을 사용하기 위한 라이브러리

  ```kotlin
  implementation 'com.google.code.gson:gson:2.8.6'
  implementation 'com.squareup.retrofit2:converter-gson:2.6.2'
  ```

- circleimageview : Circleimageview를 사용하기 위한 라이브러리

  ```kotlin
  implementation 'de.hdodenhof:circleimageview:3.0.1'
  ```

- tablayout : Tablayout을 사용하기 위한 라이브러리

  ```kotlin
  implementation 'com.google.android.material:material:1.0.0'
  ```

- Date : Current Date를 불러오기 위한 라이브러리

  ```kotlin
  implementation 'com.github.hotchemi:khronos:0.9.0'
  ```

- firebase :  Add the Firebase SDK for Google Analytics

  ```kotlin
  implementation 'com.google.firebase:firebase-analytics:17.2.0'
  implementation 'com.google.firebase:firebase-database:19.2.0'
  ```

- firebase plugin : Firebase 를 사용하기 위한 plugin

  ```kotlin
  apply plugin: 'com.google.gms.google-services'
  ```

- lottie : Lottie를 사용하기 위한 라이브러리

  ```kotlin
  implementation 'com.airbnb.android:lottie:3.3.1'
  ```

  

## Program Structure

* api: 통신 간 사용되는 interface, Object

* feature: 기능별 코드 정리

  * 각 기능에서 필요한 activity, fragment, adapter 등의 코드
  * splash
  * sign

  * chat
  * dialog
  * matching
  * movie
  * timechoice
  * mypage

* util: 직접 만든 함수들

|             Activity             |                         Description                          |
| :------------------------------: | :----------------------------------------------------------: |
|         `SplashActivity`         |        First run of the app using the Lottie Library         |
| `SignInActivity, SignUpActivity` | 1. If ID exists, Login successful & Move to MainActivity<br />2.Move to membership activity if ID does not exist |
|          `MainActivity`          | 1. Show Recommendation Movie, Selected Movie, Selected Time<br />2.Move to MyPageActivity, ModifyMovieActivity, ModifyTimeActivity, MovieSelectionActivity |
|     `MovieSelectionActivity`     | Tablayout, RecyclerView, Fragment, ViewPager를 이용한 영화 선택 알고리즘 화면 |
|       `TimeChoiceActivity`       |  Date라이브러리 시간 선택 알고리즘을 이용한 시간 선택 화면   |
|         `MyPageActivity`         | 사용자 갤러리에 접근하여 이미지 불러오기, 사용자 정보 수정 알고리즘 화면 |
|    `MatchingHistoryActivity`     |               매칭된 상대 이력을 보여주는 화면               |
|     MatchingWaitingActivity      |            매칭된 사람과 채팅시작을 결정하는 화면            |
|     MatchingDetailedActivity     | 채팅 후 매칭된 상대와 서로 수락을 여부에 따라 영화를 보러 가는 화면 |
|        `ChattingActivity`        | 파이어베이스의 실시간 데이터베이스를 이용한 실시간 채팅 화면 |
|       `PayChoiceActivity`        |     사용자가 채팅을 하기 전에 티켓을 구매할 수 있는 화면     |



# Implement key features

- splash(Lottie) : Lottie를 사용한 스플래시 화면 구현

- signIn/signUp: 회원가입과 로그인 구현 

- viewpager : Main화면에서 viewpager을 이용한 fragment간의 이동

- recyclerview : viewholder를 이용한 recyclerview를 사용한 기능 구현

- tablayout : tablayout을 이용 

- fragment : fragment를 이용

- dateUtil : dateUtil을 이용하여 시간선택 알고리즘과 특정시간에 이벤트를 발생하는 기능 구현

- chatting : Firebase's Realtime Database를 사용하여 실시간 채팅 기술 구현(매칭 알고리즘으로 선정된 매칭 상대와 채팅방 주소를 받아옴)

- ![image-20200103193914293](C:\Users\gh060\AppData\Roaming\Typora\typora-user-images\image-20200103193914293.png)

- connect Hyperlink:

- Pop-up : Custom Dialog를 이용하여 팝업 화면 구현

- load Photo from Gallery : 사용자의 갤러리에 접근하여 이미지를 바꿀 수 있도록 구현

- kotlin extension & Lambda Expression : 코틀린 확장 함수와 람다식을 이용한 서버 통신 구현

  ```kotlin
  fun <T> Call<T>.safeEnqueue(
      onError: (Throwable) -> Unit = onErrorStub,
      onResponse: (res: Response<T>) -> Unit = {}
  ) {
      this.enqueue(
          object : Callback<T> {
              override fun onFailure(call: Call<T>, t: Throwable) {
                  onError(t)
              }
  
              override fun onResponse(
                  call: Call<T>,
                  response: Response<T>
              ) {
                  onResponse(response)
              }
          }
      )
  }
  ```

- Constraint Layout :  Constraint Layout을 사용한 레이아웃 구성

- Util : 특정 시간에 이벤트가 동작할 수 있도록 기능 구현

# **Project Member**

- [김민진](https://github.com/kim003512)
- [강수빈](https://github.com/ksb0511)
- [황지혜](https://github.com/jihye0420)
- [박길현](https://github.com/G-hyeon)



