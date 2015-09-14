/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.san.luc.helloworld;

import org.junit.Assert;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.model.KieBaseModel;
import org.kie.api.builder.model.KieModuleModel;
import org.kie.api.builder.model.KieSessionModel;
import org.kie.api.conf.EqualityBehaviorOption;
import org.kie.api.conf.EventProcessingOption;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.conf.ClockTypeOption;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a sample file to launch a rule package from a rule source file.
 */
public class HelloWorldExampleProgrammatically {

    public static final void main(final String[] args) {
        // KieServices is the factory for all KIE services 
        KieServices ks = KieServices.Factory.get();

        KieModuleModel kieModuleModel = ks.newKieModuleModel();


        KieBaseModel kieBaseModel1 = kieModuleModel.newKieBaseModel( "KBase1 ")

                .setDefault( true )

                .addPackage("HelloWorldKB")

                .setEqualsBehavior( EqualityBehaviorOption.EQUALITY )

                .setEventProcessingMode( EventProcessingOption.STREAM );


        KieSessionModel ksessionModel1 = kieBaseModel1.newKieSessionModel( "KSession1" )

                .setDefault( true )

                .setType( KieSessionModel.KieSessionType.STATEFUL )

                .setClockType( ClockTypeOption.get("realtime") );


        KieFileSystem kfs = ks.newKieFileSystem();

        kfs.writeKModuleXML(kieModuleModel.toXML());

        kfs.write( "src/main/resources/HelloWorldKB/HelloWorld.drl", ks.getResources().newClassPathResource("rules/helloworld/HelloWorld.drl") );

        KieBuilder kb = ks.newKieBuilder(kfs);
        kb.buildAll();

        Assert.assertEquals(0, kb.getResults().getMessages(org.kie.api.builder.Message.Level.ERROR).size());

        KieContainer kc = ks.newKieContainer(ks.getRepository().getDefaultReleaseId());

        ks.newKieScanner(kc);
        
        KieSession ksession = kc.newKieSession("KSession1");
        StatelessKieSession statelessKieSession = kc.newStatelessKieSession();

        // Once the session is created, the application can interact with it
        // In this case it is setting a global as defined in the 
        // org/drools/examples/helloworld/HelloWorld.drl file
        ksession.setGlobal( "list",
                            new ArrayList<Object>() );

        // The application can also setup listeners
        ksession.addEventListener( new DebugAgendaEventListener() );

        // To setup a file based audit logger, uncomment the next line 
        // KieSession logger = ks.getLoggers().newFileLogger( ksession, "./helloworld" );
        
        // To setup a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );

        // The application can insert facts into the session
        final Message message = new Message();
        message.setMessage( "Hello World" );
        message.setStatus( Message.HELLO );
        ksession.insert( message );

        // and fire the rules
        ksession.fireAllRules();
        //ksession.fireAllRules(new RuleNameEqualsAgendaFilter("HelloWorld"));
        
        // Remove comment if using logging
        // logger.close();

        // and then dispose the session
        ksession.dispose();
    }
}
