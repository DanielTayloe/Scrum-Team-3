<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE module PUBLIC "-//Puppy Crawl//DTD Check Configuration 1.3//EN" "http://www.puppycrawl.com/dtds/configuration_1_3.dtd">

<!--
    This configuration file was written by the eclipse-cs plugin configuration editor
-->
<!--
    Checkstyle-Configuration: Sun Checks (Eclipse) - Scrum3
    Description: 
Slightly modified version of Sun Checks that better matches the default code formatter setting of Eclipse.
-->
<module name="Checker">
  <property name="severity" value="warning"/>
  <module name="TreeWalker">
    <property name="tabWidth" value="4"/>
    <module name="JavadocMethod">
      <property name="severity" value="ignore"/>
      <property name="suppressLoadErrors" value="true"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="JavadocType">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="JavadocVariable">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="JavadocStyle">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="ConstantName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="LocalFinalVariableName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="LocalVariableName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="MemberName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="MethodName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="PackageName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="ParameterName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="StaticVariableName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="TypeName">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="AvoidStarImport"/>
    <module name="IllegalImport"/>
    <module name="RedundantImport"/>
    <module name="UnusedImports"/>
    <module name="LineLength">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="MethodLength">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="ParameterNumber">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="EmptyForIteratorPad"/>
    <module name="GenericWhitespace"/>
    <module name="MethodParamPad"/>
    <module name="NoWhitespaceAfter">
      <property name="tokens" value="BNOT,DEC,DOT,INC,LNOT,UNARY_MINUS,UNARY_PLUS"/>
    </module>
    <module name="NoWhitespaceBefore"/>
    <module name="OperatorWrap"/>
    <module name="ParenPad"/>
    <module name="TypecastParenPad"/>
    <module name="WhitespaceAfter"/>
    <module name="WhitespaceAround">
      <property name="tokens" value="ASSIGN,BAND,BAND_ASSIGN,BOR,BOR_ASSIGN,BSR,BSR_ASSIGN,BXOR,BXOR_ASSIGN,COLON,DIV,DIV_ASSIGN,EQUAL,GE,GT,LAND,LCURLY,LE,LITERAL_ASSERT,LITERAL_CATCH,LITERAL_DO,LITERAL_ELSE,LITERAL_FINALLY,LITERAL_FOR,LITERAL_IF,LITERAL_RETURN,LITERAL_SYNCHRONIZED,LITERAL_TRY,LITERAL_WHILE,LOR,LT,MINUS,MINUS_ASSIGN,MOD,MOD_ASSIGN,NOT_EQUAL,PLUS,PLUS_ASSIGN,QUESTION,RCURLY,SL,SLIST,SL_ASSIGN,SR,SR_ASSIGN,STAR,STAR_ASSIGN,LITERAL_ASSERT,TYPE_EXTENSION_AND,WILDCARD_TYPE"/>
    </module>
    <module name="ModifierOrder"/>
    <module name="RedundantModifier"/>
    <module name="AvoidNestedBlocks"/>
    <module name="EmptyBlock"/>
    <module name="LeftCurly"/>
    <module name="NeedBraces"/>
    <module name="RightCurly"/>
    <module name="AvoidInlineConditionals">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="EmptyStatement">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="EqualsHashCode">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="HiddenField">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="IllegalInstantiation">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="InnerAssignment">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="MagicNumber">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="MissingSwitchDefault">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="SimplifyBooleanExpression">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="SimplifyBooleanReturn">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="DesignForExtension">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="FinalClass">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="HideUtilityClassConstructor">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="InterfaceIsType">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="VisibilityModifier">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="ArrayTypeStyle">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="FinalParameters">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="TodoComment">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
    <module name="UpperEll">
      <property name="severity" value="ignore"/>
      <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
    </module>
  </module>
  <module name="JavadocPackage">
    <property name="severity" value="ignore"/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
  <module name="NewlineAtEndOfFile">
    <property name="severity" value="ignore"/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
  <module name="Translation">
    <property name="severity" value="ignore"/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
  <module name="FileLength">
    <property name="severity" value="ignore"/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
  <module name="FileTabCharacter">
    <property name="severity" value="ignore"/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
  <module name="RegexpSingleline">
    <property name="severity" value="ignore"/>
    <property name="format" value="\s+$"/>
    <property name="message" value="Line has trailing spaces."/>
    <metadata name="net.sf.eclipsecs.core.lastEnabledSeverity" value="inherit"/>
  </module>
</module>
