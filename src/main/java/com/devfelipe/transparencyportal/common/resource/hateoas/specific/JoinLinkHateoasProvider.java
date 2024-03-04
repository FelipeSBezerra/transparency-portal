package com.devfelipe.transparencyportal.common.resource.hateoas.specific;

import com.devfelipe.transparencyportal.common.dto.BaseResponseDto;

/**
 * Interface defining the contract for providing HATEOAS join links to response DTOs.
 *
 * @param <ID>   The type of the identifier.
 * @param <Resp> Type of the response DTO, extending {@link BaseResponseDto}.
 */
public interface JoinLinkHateoasProvider<ID ,Resp extends BaseResponseDto<ID>> {

    /**
     * Adds HATEOAS join links to the response DTO.
     *
     * @param response The response DTO to which join links will be added.
     */
    void addJoinLink(Resp response);
}
