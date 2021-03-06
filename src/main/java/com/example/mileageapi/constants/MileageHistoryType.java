package com.example.mileageapi.constants;

public enum MileageHistoryType {

  // 내용 점수 조건 (1자 이상의 컨텐츠 작성)
  CONTENT_ADD,
  // 내용 접수 조건 (1자 이상의 사진 첨부)
  PHOTO_ADD,
  // 첫 번쨰 리뷰 작성시
  REVIEW_ADD,
  // 내용 삭제 조건
  CONTENT_REMOVE,
  // 사진 삭제 조건
  PHOTO_REMOVE,
  // 첫번째 장소의 리뷰 삭제
  REVIEW_REMOVE,

  // 컨텐츠 수정
  CONTENT_MOD,
  // 사진 추가
  PHOTO_MOD
}
