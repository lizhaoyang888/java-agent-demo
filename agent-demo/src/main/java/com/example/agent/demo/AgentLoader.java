package com.example.agent.demo;

import net.bytebuddy.agent.ByteBuddyAgent;

import java.io.File;

/**
 * @Author: lichaoyang
 * @Date: 2020-06-15 02:10
 */
public class AgentLoader {

    public static void main(String[] args) {
        String agent_jar = "/Users/lizhaoyang/IdeaProjects/java-agent-demo-2/agent-demo/target/agent-demo-0.0.1-SNAPSHOT.jar";
        String pid = "12190";
        ByteBuddyAgent.attach(new File(agent_jar),pid);
    }
}
