package io.ybt;

import io.nadron.app.Game;
import io.nadron.app.GameRoom;
import io.nadron.app.impl.GameRoomSession.GameRoomSessionBuilder;
import io.nadron.app.impl.SimpleGame;
import io.nadron.example.lostdecade.LDRoom;
import io.nadron.protocols.Protocol;
import io.nadron.service.LookupService;
import io.nadron.service.impl.SimpleLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import java.util.HashMap;
import java.util.Map;

/**
 * This class contains the spring configuration for the nadron library user.
 * The only bean that is compulsory to be declared is lookupService bean.
 * Otherwise the program will terminate with a context load error from spring
 * framework. The other beans declared can also be created using **new**
 * operator as per programmer convenience.
 *
 * @author Abraham Menacherry.
 */
@Configuration
@ImportResource("classpath:/beans/beans.xml")
public class SpringConfig {

//    @Autowired
//    @Qualifier("messageBufferProtocol")
//    private Protocol messageBufferProtocol;

    @Autowired
    @Qualifier("webSocketProtocol")
    private Protocol webSocketProtocol;

//    @Autowired
//    @Qualifier("msgPackProtocol")
//    private Protocol msgPackProtocol;

    public
    @Bean(name = "LDGame")
    Game ldGame() {
        return new SimpleGame(1, "LDGame");
    }

    public
    @Bean(name = "LDGameRoom")
    GameRoom ldGameRoom() {
        GameRoomSessionBuilder sessionBuilder = new GameRoomSessionBuilder();
        sessionBuilder.parentGame(ldGame()).gameRoomName("LDGameRoom")
                .protocol(webSocketProtocol);
        LDRoom room = new LDRoom(sessionBuilder);
        return room;
    }
//
//    public
//    @Bean
//    World world() {
//        World world = new World();
//        world.setAlive(200000);
//        world.setUndead(1);
//        return world;
//    }
//
//    public
//    @Bean
//    Defender defender() {
//        Defender defender = new Defender();
//        defender.setWorld(world());
//        return defender;
//    }
//
//    public
//    @Bean
//    Zombie zombie() {
//        Zombie zombie = new Zombie();
//        zombie.setWorld(world());
//        return zombie;
//    }
//
//
//    public
//    @Bean
//    Game zombieGame() {
//        Game game = new SimpleGame(2, "Zombie");
//        return game;
//    }
//
//    public
//    @Bean(name = "Zombie_Room")
//    GameRoom zombieRoom() {
//        GameRoomSessionBuilder sessionBuilder = new GameRoomSessionBuilder();
//        sessionBuilder.parentGame(zombieGame())
//                .gameRoomName("Zombie_ROOM")
//                .protocol(msgPackProtocol);
////                .protocol(messageBufferProtocol);
//        ZombieRoom room = new ZombieRoom(sessionBuilder);
//        room.setDefender(defender());
//        room.setZombie(zombie());
//        return room;
//    }


    public
    @Bean(name = "lookupService")
    LookupService lookupService() {
        Map<String, GameRoom> refKeyGameRoomMap = new HashMap<String, GameRoom>();
        refKeyGameRoomMap.put("LDGameRoom", ldGameRoom());
//        refKeyGameRoomMap.put("Zombie_ROOM", zombieRoom());
        LookupService service = new SimpleLookupService(refKeyGameRoomMap);
        return service;
    }
}
