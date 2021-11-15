package in.microsoft.restcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Semantics")
public class SemanticController {
	
	@GetMapping("/getOne")
	public String getSem() {
		return "ok";
	}
	
	@PostMapping("/save")
	public String saveSem() {
		return "ok";
	}

}
