package authorization;

import java.util.Map;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import reactor.core.publisher.Mono;

/**
 * @author VuDo
 * @since 2/12/2021
 */
public interface ReactiveOAuth2RequestFactory {

  /**
   * Create a new {@link AuthorizationRequest} extracting all the needed information from the incoming parameter map, and initializing all individual fields on
   * the {@link AuthorizationRequest} to reasonable values. When a class uses the factory to create an {@link AuthorizationRequest}, it should not need to
   * access the parameter map directly afterwards.
   * <p>
   * Typical implementations would initialize the individual fields on the {@link AuthorizationRequest} with the values requested in the original parameter map.
   * It may also load the client details from the client id provided and validate the grant type and scopes, populating any fields in the request that are known
   * only to the authorization server.
   *
   * @param authorizationParameters the parameters in the request
   * @return a new AuthorizationRequest
   */
  Mono<AuthorizationRequest> createAuthorizationRequest(Map<String, String> authorizationParameters);

  /**
   * Create a new {@link OAuth2Request} by extracting the needed information from the current {@link AuthorizationRequest} object.
   *
   * @param request the request to be converted
   * @return an immutable object for storage
   */
  Mono<OAuth2Request> createOAuth2Request(AuthorizationRequest request);

  /**
   * Create a new {@link OAuth2Request} by extracting the needed information from the current {@link TokenRequest} object.
   *
   * @param client       TODO
   * @param tokenRequest the request to be converted
   * @return am immutable object for storage
   */
  Mono<OAuth2Request> createOAuth2Request(ClientDetails client, TokenRequest tokenRequest);

  /**
   * Create a new {@link TokenRequest} by extracting the needed information from the incoming request parameter map.
   *
   * @param requestParameters   the parameters in the request
   * @param authenticatedClient the client that authenticated during the token request
   * @return a new TokenRequest
   */
  Mono<TokenRequest> createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient);

  /**
   * Create a new {@link TokenRequest} from an {@link AuthorizationRequest}. Principally used by the AuthorizationEndpoint during the implicit flow.
   *
   * @param authorizationRequest the incoming request
   * @param grantType            the grant type for the token request
   * @return a new token request
   */
  Mono<TokenRequest> createTokenRequest(AuthorizationRequest authorizationRequest, String grantType);
}
