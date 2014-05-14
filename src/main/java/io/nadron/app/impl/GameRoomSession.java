package io.nadron.app.impl;

import io.nadron.app.*;
import io.nadron.concurrent.LaneStrategy;
import io.nadron.concurrent.LaneStrategy.LaneStrategies;
import io.nadron.event.*;
import io.nadron.event.impl.*;
import io.nadron.protocols.Protocol;
import io.nadron.service.GameStateManagerService;
import io.nadron.service.impl.GameStateManager;
import io.netty.util.internal.ConcurrentSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;


public abstract class GameRoomSession extends DefaultSession implements GameRoom {
    private static final Logger LOG = LoggerFactory.getLogger(GameRoomSession.class);

    /**
     * The name of the game room, preferably unique across multiple games.
     */
    protected String gameRoomName;
    /**
     * The parent {@link SimpleGame} reference of this game room.
     */
    protected Game parentGame;
    /**
     * Each game room has separate state manager instances. This variable will
     * manage the state for all the {@link DefaultPlayer}s connected to this game room.
     */
    protected GameStateManagerService stateManager;

    /**
     * The set of sessions in this object.
     */
    protected Set<PlayerSession> sessions;

    protected ConcurrentSet<ChatMessage> chatMessages;


    /**
     * Each game room has its own protocol for communication with client.
     */
    protected Protocol protocol;

    protected SessionFactory sessionFactory;

    protected GameRoomSession(GameRoomSessionBuilder gameRoomSessionBuilder) {
        super(gameRoomSessionBuilder);
        this.chatMessages = gameRoomSessionBuilder.chatMessages;
        this.sessions = gameRoomSessionBuilder.sessions;
        this.parentGame = gameRoomSessionBuilder.parentGame;
        this.gameRoomName = gameRoomSessionBuilder.gameRoomName;
        this.protocol = gameRoomSessionBuilder.protocol;
        this.stateManager = gameRoomSessionBuilder.stateManager;
        this.sessionFactory = gameRoomSessionBuilder.sessionFactory;

        if (null == gameRoomSessionBuilder.eventDispatcher) {
            this.eventDispatcher = EventDispatchers.newJetlangEventDispatcher(
                    this, gameRoomSessionBuilder.laneStrategy);
        }
    }

    public static class GameRoomSessionBuilder extends SessionBuilder {
        protected ConcurrentSet<ChatMessage> chatMessages;
        protected Set<PlayerSession> sessions;
        protected Game parentGame;
        protected String gameRoomName;
        protected Protocol protocol;
        protected LaneStrategy<String, ExecutorService, GameRoom> laneStrategy;
        protected GameStateManagerService stateManager;
        protected SessionFactory sessionFactory;

        @Override
        protected void validateAndSetValues() {
            if (null == id) {
                id = String.valueOf(ID_GENERATOR_SERVICE.generate());
                LOG.trace("GameRoomSession.GameRoomSessionBuilder.id: {}", id);
                //id = String.valueOf(ID_GENERATOR_SERVICE.generateFor(GameRoomSession.class));
            }
            if (null == sessionAttributes) {
                sessionAttributes = new HashMap<String, Object>();
            }
            if (null == sessions) {
                sessions = new HashSet<PlayerSession>();
            }
            if (null == chatMessages) {
                chatMessages = new ConcurrentSet<ChatMessage>();
                chatMessages.add(new ChatMessage("23", "type", "Украину обманывают, обещают золотые горы - Лондон и Париж. Чтобы досадить России, Запад стремится дестабилизировать Украину. Ни выборов, ни мира, ничего не будет. Весь народ Украины, никогда не примет бандеровскую сволочь. Только размежевание способно остановить кровопролитие.",
                        234356, 62423452, "source", "sourceName", "destination", "status"));
                chatMessages.add(new ChatMessage("25", "type", "Карл Маркс в одной из своих работ говорил о том, что ход истории частенько складывается таким образом, чтобы человечество весело расставалось со своим прошлым. Чехов тоже как-то обмолвился о том, что человечество прощается с прошлым, смеясь.",
                        234356, 62423452, "source", "sourceName", "destination", "status"));
                chatMessages.add(new ChatMessage("26", "type", "Наверное, больше всех о масштабе перемен могла бы поведать… почта России. Ибо за последнее десятилетие именно почта стала свидетелем того, как почти исчезли из обращения бумажные письма. Сегодня 90 % всей переписки приходится на эсэмэски и переписку в Интернете. И разве что старомодные чудаки да древние бабушки из глухих деревень все еще отправляют друг другу открытки к праздникам по почте.",
                        234356, 62423452, "source", "sourceName", "destination", "status"));
                chatMessages.add(new ChatMessage("27", "type", "История развития Интернета отражает ту же тенденцию: месседжи становятся все короче, а обмен сообщениями — все быстрее. Неторопливые пользователи сетей не успевают устанавливать себе все новые и новые программы мгновенного обмена сообщениями: привычные IСQ и скайп давно потеснили MailAgent, Windows Live Messenger, IRC, AIM, MSN, Yahoo!",
                        234356, 62423452, "source", "sourceName", "destination", "status"));
                chatMessages.add(new ChatMessage("28", "type", "Скорости зашкаливают, плотность подачи информации растет, расстояния сжимаются, сообщения сокращаются, а в целом время летит быстрее, и люди живут уже совсем в другом ритме. А начиналось все с невинных, казалось бы, вещей. С компьютеров, с Интернета, с мобильников. Со спутниковой связи, наконец, идею которой предложил еще в 1945 году английский фантаст Артур",
                        234356, 62423452, "source", "sourceName", "destination", "status"));
            }
            if (null == laneStrategy) {
                laneStrategy = LaneStrategies.GROUP_BY_ROOM;
            }
            if (null == stateManager) {
                stateManager = new GameStateManager();
            }
            if (null == sessionFactory) {
                sessionFactory = Sessions.INSTANCE;
            }
            creationTime = System.currentTimeMillis();
        }

        public GameRoomSessionBuilder sessions(Set<PlayerSession> sessions) {
            this.sessions = sessions;
            return this;
        }

        public GameRoomSessionBuilder parentGame(Game parentGame) {
            this.parentGame = parentGame;
            return this;
        }

        public GameRoomSessionBuilder gameRoomName(String gameRoomName) {
            this.gameRoomName = gameRoomName;
            return this;
        }

        public GameRoomSessionBuilder protocol(Protocol protocol) {
            this.protocol = protocol;
            return this;
        }

        public GameRoomSessionBuilder laneStrategy(
                LaneStrategy<String, ExecutorService, GameRoom> laneStrategy) {
            this.laneStrategy = laneStrategy;
            return this;
        }

        public GameRoomSessionBuilder stateManager(
                GameStateManagerService gameStateManagerService) {
            this.stateManager = gameStateManagerService;
            return this;
        }

        public GameRoomSessionBuilder sessionFactory(SessionFactory sessionFactory) {
            this.sessionFactory = sessionFactory;
            return this;
        }
    }

    @Override
    public PlayerSession createPlayerSession(Player player) {
        PlayerSession playerSession = getSessionInstance(player);
        return playerSession;
    }

    @Override
    public abstract void onLogin(PlayerSession playerSession);

    @Override
    public synchronized boolean connectSession(PlayerSession playerSession) {
        if (!isShuttingDown) {
            playerSession.setStatus(Session.Status.CONNECTING);
            LOG.debug("Добавление playerSession в sessions");
            sessions.add(playerSession);
            playerSession.setGameRoom(this);
            LOG.trace("Protocol to be applied is: {}", protocol.getClass().getName());
            protocol.applyProtocol(playerSession, true);
            createAndAddEventHandlers(playerSession);
            playerSession.setStatus(Session.Status.CONNECTED);
            afterSessionConnect(playerSession);
            return true;
            // TODO send event to all other sessions?
        } else {
            LOG.warn("Game Room is shutting down, playerSession {} {}",
                    playerSession, "will not be connected!");
            return false;
        }
    }

    @Override
    public void afterSessionConnect(PlayerSession playerSession) {
        LOG.trace("afterSessionConnect");
        if (!chatMessages.isEmpty()) {
            for (ChatMessage chatMessage : chatMessages) {
                playerSession.getTcpSender().sendMessage(Events.chatMessageEvent(chatMessage));
            }
        }
        if (!sessions.isEmpty()) {
            for (PlayerSession ps : sessions) {
                playerSession.getTcpSender().sendMessage(Events.newUserLoginEvent(new UserInfo(String.valueOf(ps.getId()), "Vasya", "role", "status", "isYb", "details")));
            }
        }
    }


    public synchronized boolean disconnectSession(PlayerSession playerSession) {
        final boolean removeHandlers = this.eventDispatcher.removeHandlersForSession(playerSession);
        playerSession.getEventDispatcher().clear(); // remove network handlers of the session.
        LOG.debug("Удаление playerSession из sessions");
        return (removeHandlers && sessions.remove(playerSession));
    }

    @Override
    public void send(Event event) {
        onEvent(event);
    }

    @Override
    public void sendBroadcast(NetworkEvent networkEvent) {
        onEvent(networkEvent);
    }

    @Override
    public void sendNewUserBroadcast(NewUserLoginEvent newUserLoginEvent) {
        onEvent(newUserLoginEvent);
    }

    @Override
    public void sendChatMessageBroadcast(ChatMessageEvent chatMessageEvent) {
        onEvent(chatMessageEvent);
    }

    @Override
    public synchronized void close() {
        isShuttingDown = true;
        for (PlayerSession session : sessions) {
            session.close();
        }
    }

    public PlayerSession getSessionInstance(Player player) {
        PlayerSession playerSession = sessionFactory.newPlayerSession(this, player);
        return playerSession;
    }

    @Override
    public ConcurrentSet<ChatMessage> getChatMessages() {
        return chatMessages;
    }

    @Override
    public Set<PlayerSession> getSessions() {
        return sessions;
    }

    @Override
    public void setSessions(Set<PlayerSession> sessions) {
        this.sessions = sessions;
    }

    @Override
    public String getGameRoomName() {
        return gameRoomName;
    }

    @Override
    public void setGameRoomName(String gameRoomName) {
        this.gameRoomName = gameRoomName;
    }

    @Override
    public Game getParentGame() {
        return parentGame;
    }

    @Override
    public void setParentGame(Game parentGame) {
        this.parentGame = parentGame;
    }

    @Override
    public void setStateManager(GameStateManagerService stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public GameStateManagerService getStateManager() {
        return stateManager;
    }

    @Override
    public Protocol getProtocol() {
        return protocol;
    }

    @Override
    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    @Override
    public SessionFactory getFactory() {
        return sessionFactory;
    }

    @Override
    public void setFactory(SessionFactory factory) {
        this.sessionFactory = factory;
    }

    @Override
    public boolean isShuttingDown() {
        return isShuttingDown;
    }

    public void setShuttingDown(boolean isShuttingDown) {
        this.isShuttingDown = isShuttingDown;
    }

    /**
     * Method which will create and add event handlers of the player session to
     * the Game Room's EventDispatcher.
     *
     * @param playerSession The session for which the event handlers are created.
     */
    protected void createAndAddEventHandlers(PlayerSession playerSession) {
        // Create a network event listener for the player session.
        EventHandler networkEventHandler = new NetworkEventListener(playerSession);
        EventHandler newUserEventHandler = new NewUserLoginEventListener(playerSession);
        EventHandler chatMessageEventHandler = new ChatMessageEventListener(playerSession);
        // Add the handler to the game room's EventDispatcher so that it will
        // pass game room network events to player session session.
        this.eventDispatcher.addHandler(networkEventHandler);
        this.eventDispatcher.addHandler(newUserEventHandler);
        this.eventDispatcher.addHandler(chatMessageEventHandler);
        LOG.trace("Added Network handler to "
                + "EventDispatcher of GameRoom {}, for session: {}", this,
                playerSession);
    }
}
