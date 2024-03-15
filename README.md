# 1. 프로젝트 정보

학교 강의 'JAVA프로그래밍실습'의 기말 자유 주제 개인 과제의 연장선<br>
메이플스토리 모작 프로젝트<br>
JAVA의 Swing 라이브러리만을 사용하여 개발<br>
모든 로직 직접 구현

개발 기간 : 21/11 ~ 23/01<br>

# 2. 프로젝트 구조

### 1. 구조도
<img src="https://github.com/slllldka/MyGame/assets/121309640/21073ed4-28a5-4f87-9865-afda3d03bd57" width="400" height="200"/>

### 2. ERD
<img src="https://github.com/slllldka/MyGame/assets/121309640/d121d234-975c-4707-a972-d90643ee46b3"/>

# 3. 프로젝트 설명

### 1. 몬스터 사냥, 자원 소모, 재화 획득
<img src="https://github.com/slllldka/MyGame/assets/121309640/a369ab52-e6b7-4722-935e-4e0827cc7d62" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/884cefa2-3c5c-4bf9-8cb5-66f29932074e" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/4f7d2d1c-98aa-45cb-b503-088994e510b4" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/2896e6ba-a6eb-4c21-93b4-a499f24831dc" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/f2d72040-d036-4a76-a12d-0cd9082c9af0" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/1c70b385-bf29-4c53-84df-4c08c6c35a73" width="400" height="300"/>

공격 시 MP 소모<br>
피격 시 HP 소모<br>
사망 시 경험치 감소<br>
몬스터 사냥 시 경험치 획득<br>
아이템 위에서 줍기(Z)를 누르면 아이템 획득<br>
소비 아이템 더블 클릭 시 소모<br>

유저의 HP, MP, 경험치가 변화될 때 마다 DB에 업데이트<br>
유저가 아이템을 획득, 소모할 때 마다 DB에 업데이트<br>


### 2. 상점
<img src="https://github.com/slllldka/MyGame/assets/121309640/91596cf1-0ff5-43eb-8570-bdb49a6778fb" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/bc2371b1-1538-4ae9-ae77-625f5ea7894c" width="400" height="300"/>

아이템 구매, 판매 기능<br>
유저가 아이템을 구매 또는 판매할 때 마다 DB에 업데이트<br>
    
### 3. 커스텀 키세팅 및 퀵슬롯
<img src="https://github.com/slllldka/MyGame/assets/121309640/14c72c5d-c720-4a62-8f76-d1e3a0e1a556" width="400" height="300"/>
<img src="https://github.com/slllldka/MyGame/assets/121309640/a4ab4273-71be-4659-896d-f766b4d9206b" width="400" height="300"/>

기본 키 위치 변경, 소비 아이템 등록 , 퀵슬롯 변경 기능<br>
변경 후 확인 버튼을 누르면 DB에 변경사항 업데이트<br>
    
    
