<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<html>
<head>
<title>Bid Amount</title>
</head>
<!-- page 138,143,196 to add Items to the card -->
<body style="height: 204px; ">
	<f:view>
		<h:form>
			<h:outputText value="Item #{bidBean.itemId}.  "></h:outputText>
			<h:outputText
				value=", Amount #{bidBean.amount}  is added successfully."></h:outputText>
		</h:form>
	</f:view>
</body>
</html>