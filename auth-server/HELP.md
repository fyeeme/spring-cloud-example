# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.0/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.0/gradle-plugin/reference/html/#build-image)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-security-oauth2-server)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-security)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.7.0/reference/htmlsingle/#using-boot-devtools)

### Guides

1. url
    http://localhost:9000/.well-known/openid-configuration

```json
{
    "issuer": "http://localhost:9000",
    "authorization_endpoint": "http://localhost:9000/oauth2/authorize",
    "token_endpoint": "http://localhost:9000/oauth2/token",
    "token_endpoint_auth_methods_supported": [
        "client_secret_basic",
        "client_secret_post",
        "client_secret_jwt",
        "private_key_jwt"
    ],
    "jwks_uri": "http://localhost:9000/oauth2/jwks",
    "userinfo_endpoint": "http://localhost:9000/userinfo",
    "response_types_supported": [
      "code"
    ],
    "grant_types_supported": [
      "authorization_code",
      "client_credentials",
      "refresh_token"
    ],
    "subject_types_supported": [
      "public"
    ],
    "id_token_signing_alg_values_supported": [
      "RS256"
    ],
    "scopes_supported": [
      "openid"
    ]
}
```

2. authorize url 

http://localhost:9000/oauth2/authorize

params: 

```json
{
  response_type: 'code',
  client_id: 'messaging-client',
  scope: 'openid',
  redirect_uri: 'http://127.0.0.1:3000/oauth2/authorized',
  code_challenge: code_challenge,
  code_challenge_method: 'S256',
  state: state,
}
```

3. authorization token url

POST http://localhost:9000/oauth2/token HTTP/1.1
Content-Type: application/x-www-form-urlencoded
Authorization: `Basic ${btoa('messaging-client:secret')}`,

```json
{
  client_id: 'messaging-client',
  redirect_uri: 'http://127.0.0.1:3000/oauth2/authorized',
  grant_type: 'authorization_code',
  code: route.query.code,
  code_verifier: code_verifier,
  state: state,
}
```

3.1 refresh token url

ref: https://datatracker.ietf.org/doc/html/rfc6749#section-6

POST http://localhost:9000/oauth2/token HTTP/1.1
Authorization: `Basic ${btoa('messaging-client:secret')}`,
Content-Type: application/x-www-form-urlencoded

```json
{
  grant_type: 'refresh_token',
  refresh_token: 'tGzv3JOkF0XG5Qx2TlKWIA'
}
```

grant_type=refresh_token&refresh_token=tGzv3JOkF0XG5Qx2TlKWIA
4. important

if you are using vue or react as client, you should pay attention to **PKCE**

https://www.valentinog.com/blog/oauth2/
https://coolgk.medium.com/oauth-pkce-generate-code-verifier-and-code-challenge-in-ie11-and-modern-browsers-e0b8864956ed

5. endpoints 

```java 

public static Builder builder() {
    return new Builder()
        .authorizationEndpoint("/oauth2/authorize")
        .tokenEndpoint("/oauth2/token")
        .jwkSetEndpoint("/oauth2/jwks")
        .tokenRevocationEndpoint("/oauth2/revoke")
        .tokenIntrospectionEndpoint("/oauth2/introspect")
        .oidcClientRegistrationEndpoint("/connect/register")
        .oidcUserInfoEndpoint("/userinfo");
    }

```

6. For example, when the `value` for `OAuth2TokenType` is:

* `code`, then `OAuth2AuthorizationCode` is generated.
* `access_token`, then `OAuth2AccessToken` is generated.
* `refresh_token`, then `OAuth2RefreshToken` is generated.
* `id_token`, then `OidcIdToken` is generated.
### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


### TODO 

- [ ] using jpa replace jdbc 
  - https://docs.spring.io/spring-authorization-server/docs/current/reference/html/guides/how-to-jpa.html
