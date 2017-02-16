<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<f:view>
	    <p>
	        <h:message id="errors" for="Amount" style="color:red"/>
	        &nbsp; Welcome <%= request.getRemoteUser() %>!
	    </p>
	 	<h:form>
			<h:panelGrid width="50" columns="2" border="1">
				<h:outputText value="BidderId"></h:outputText>
				<h:inputText id="BidderId" value="#{bidBean.bidderId}"></h:inputText>
				<h:outputText value="ItemId"></h:outputText>
				<h:inputText id="ItemId" value="#{bidBean.itemId}"></h:inputText>
				<h:outputText value="Amount"></h:outputText>
				<h:inputText id="Amount" value="#{bidBean.amount}" required="true">
			  		<f:validateLongRange minimum="1" maximum="500"/>
			  	</h:inputText>
				<h:commandButton action="#{bidBean.addBid}" value="Add Bid"></h:commandButton>
			</h:panelGrid>
		</h:form>
	</f:view>
</body>
</html>