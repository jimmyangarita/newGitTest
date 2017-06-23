"""
This script starts an edit session, creates a JMS Servers, 
targets the jms servers to the ADF server 
Creates a jms topics, two jms queues and two connections factories.
The ms queues and topics are targeted using sub-deployments. 
"""

import sys
from java.lang import System

print "Starting the script ..."
connect('weblogic','Passw0rd','t3://localhost:7001')
edit()
startEdit()

servermb=getMBean("Servers/AdminServer")
if servermb is None:
    print 'Value is Null'
else:
    jMSServer1mb = create('TESTJMSServer','JMSServer')
    jMSServer1mb.addTarget(servermb)

    jmsMySystemResource = create("TESTJMSSystemModule","JMSSystemResource")
    jmsMySystemResource.addTarget(servermb)

    subDep1mb = jmsMySystemResource.createSubDeployment('TESTJMSSystemModuleSub')
    subDep1mb.addTarget(jMSServer1mb)

    theJMSResource = jmsMySystemResource.getJMSResource()

    connfact1 = theJMSResource.createConnectionFactory('ApiFaultQueueConnectionFactory')
    connfact1.setJNDIName('jms.ApiFaultQueueConnectionFactory')
    connfact1.setSubDeploymentName('TESTJMSSystemModuleSub')
    connfact1.transactionParams.setXAConnectionFactoryEnabled(1)
    
    jmsqueue1 = theJMSResource.createQueue('ApiFaultQueue')
    jmsqueue1.setJNDIName('jms.ApiFaultQueue')
    jmsqueue1.setSubDeploymentName('TESTJMSSystemModuleSub')
	
    connfact2 = theJMSResource.createConnectionFactory('OrderBillingQueueConnectionFactory')
    connfact2.setJNDIName('jms.OrderBillingQueueConnectionFactory')
    connfact2.setSubDeploymentName('TESTJMSSystemModuleSub')
    connfact2.transactionParams.setXAConnectionFactoryEnabled(1)
    
    jmsqueue2 = theJMSResource.createQueue('OrderBillingQueue')
    jmsqueue2.setJNDIName('jms.OrderBillingQueue')
    jmsqueue2.setSubDeploymentName('TESTJMSSystemModuleSub')	
	
	
try:
    save()
    activate(block="true")
    print "script returns SUCCESS"   
except:
    print "Error while trying to save and/or activate!!!"
    dumpStack()