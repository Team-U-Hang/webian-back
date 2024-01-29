package uhang.uhang.upgrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uhang.uhang.upgrade.domain.Upgrade;
import uhang.uhang.upgrade.domain.repository.UpgradeRepository;
import uhang.uhang.upgrade.dto.UpgradeDto;
import uhang.uhang.upgrade.service.UpgradeService;
/*
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/upgrade")
public class UpgradeController {
    private final UpgradeService upgradeService;

    @PostMapping("/upgrade")
    public int memberjoin (@RequestBody requestDto dto) {
        return upgradeService.join(dto);
    }
}
*/


@RestController
public class UpgradeController{
    private final UpgradeService upgradeService;

    @Autowired
    public UpgradeController(UpgradeService postService) {
        this.upgradeService = postService;
    }
/*
    @GetMapping("/upgrade ")
    public String showUpgradeForm(){
        return "upgrade";

    }

 */
    /*
@GetMapping("/upgrade")
public String getUpgradePage() {
    // GET 요청에 대한 처리 로직 추가
    return "upgrade"; // 혹은 적절한 뷰 페이지 이름 반환
}

*/

@PostMapping("upgrade")
    public Upgrade createUpgrade(@RequestBody Upgrade upgrade) {
        return upgradeService.saveUpgrade(upgrade);
    }
    /*

    @PostMapping("/upgrade")  //post 방식으로 /articles/create 요청이 들어오면, 아래의 메소드 실행
    public String createUpgrade(UpgradeDto form ) {  //폼 태그의 데이터가 ArticleForm 객체로 만들어 진다
        System.out.println(form.toString()); // ArticleForm 객체 정보를 확인!

        // 1. DTO 를 Entity 로 변환!
        Upgrade upgrade = form.toEntity();
        System.out.println(upgrade.toString());

        // 2. Repository 에게 Entity 를 DB 안에 저장하게 함!
       // Upgrade saved = UpgradeRepository.save(upgrade);
        Upgrade saved = upgradeRepository.save(upgrade);
        System.out.println(saved.toString());
       return "redirect:/upgrade";
//        model.addAttribute("savedUpgrade", saved); // savedUpgrade라는 이름으로 모델에 추가

     //   return "upgradeResult"; // 적절한 뷰 페이지로 이동
    }

*/

}