<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>

        Insert title here

        </title>
    </head>
    <body>
      <StyledHeader>
                <HeaderContainer>
                    <HeaderLogoA>
                        <HeaderLogo src="https://upload.wikimedia.org/wikipedia/commons/e/ef/Stack_Overflow_icon.svg" alt="Logo" />
                    </HeaderLogoA>
                    <HeaderNavigator title1={"About"} href1={""}/>
                    <HeaderNavigator title2={"Products"} href2={""}/>
                    <HeaderNavigator title3={"For Teams"} href3={""}/>
                    <HeaderSearchBar>
                        <HeaderSearchBarIcon src="https://upload.wikimedia.org/wikipedia/commons/e/ef/Stack_Overflow_icon.svg" alt="Icon"/>
                        <HeaderSearchBarInput name="q" type="text" role="combobox" placeholder="Search…" value="" autocomplete="off" maxlength="240" class="s-input s-input__search js-search-field " aria-label="Search" aria-controls="top-search" data-controller="s-popover" data-action="focus->s-popover#show" data-s-popover-placement="bottom-start" aria-expanded="false">
                        </HeaderSearchBarInput>
                    </HeaderSearchBar>
                    <HeaderToolBar>
                        <HeaderToolBarLoginContainer>
                          <HeaderToolBarLogin href ="/login">Log in</HeaderToolBarLogin>
                        </HeaderToolBarLoginContainer>
                        <HeaderToolBarSignUpContainer>
                          <HeaderToolBarSignUp href ="/signup">Sign Up</HeaderToolBarSignUp>
                        </HeaderToolBarSignUpContainer>
                    </HeaderToolBar>
                </HeaderContainer>
            </StyledHeader>
    </body>
</html>