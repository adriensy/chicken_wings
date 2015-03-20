package fr.esgi.demo.web;

import fr.esgi.demo.web.dto.GameDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/game")
public class GameController {

    private List<GameDto> gameDtos;

    @RequestMapping(method = RequestMethod.GET)
    public GameDto getGames() {
        GameDto gameDto = new GameDto();

        gameDto.setId(1L);
        gameDto.setName("sdfsd");
        gameDto.setType(GameDto.Type.FPS);

        return gameDto;
    }
}
