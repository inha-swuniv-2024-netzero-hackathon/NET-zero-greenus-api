package inha.edu.upcyclingapp.service;

import inha.edu.upcyclingapp.model.ai.FoundationModel;
import inha.edu.upcyclingapp.model.ai.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuestionAiService {

    private final FoundationModel foundationModel;

    public String getAnswer(String question) {
        String response = foundationModel
                .call(new Message.UserMessage(getPrompt(question), null))
                .join();

        if (response.equalsIgnoreCase("ERROR")) {
            throw new RuntimeException("죄송합니다. 응답을 만들지 못했습니다.");
        }
        return response;
    }

    private String getPrompt(String question) {
        return """
                너는 적금 관련 Q&A 상담 업무를 하는 상담원이야.
                너가 숙지해야 하는 내용은 다음과 같아
                
                1. 계약기간? 1년 or 2년(가입 조건에 맞게)
                2. 저축한도? 100만원 이하
                3.고시이자율? 정기적립식 연1.4%, 자유적립식 연1.3%
                4. 우대요건 인정기간? A. 신규일로부터 만기일 전전달 말일까지
                5. 우대 조건? A. 다회용기 포장 인증 월 2회 이상 충족할 경우
                	대중교통 이용 인증 월 2회 이상 충족할 경우
                	걸음 수 인증 인증 월 2회 이상 충족할 경우
                	공유자전거 이용 인증 월 2회 이상 충족할 경우
                	해양쓰레기 수집 인증 월 2회 이상 충족할 경우
                	연 0.5%우대
                6. 중도해지 이자율? A. 1개월 미만 : 연0.100%
                	1개월 이상 : 연0.250%
                	3개월이상 : 기본이자율 × (1- 차감율) × 경과월수 / 계약월수
                	(단, 연0.500% 미만으로 산출될 경우 연 0.500% 적용)
                	※ 차감율 50% 적용(2016.6.20 현재)
                7. 만기후이자율?
                만기후 1개월 이내 : 만기일 당시의 가입기간에 해당하는 일반정기적금 연 이자율의 1/2
                만기후 1개월 초과 6개월 이내 : 만기일 당시의 가입기간에 해당하는 일반정기적금 연 이자율 1/4
                만기후 6개월 초과 : 연 0.300%
                만약 이자율이 변경될 경우 기간 구분하여 변경일부터 변경된 이자율 적용
                8. 이자지급방법? A. 만기일시지급식
                
                추가로 너가 모르는 것이 있다면 상담 직원에게 유선 문의하라고 안내하거나 (02-1234-5678), 홈페이지(www.green.com)를 참고하라고 안내해줘
                
                사용자의 질문은 다음과 같아
                """ + question;
    }
}

