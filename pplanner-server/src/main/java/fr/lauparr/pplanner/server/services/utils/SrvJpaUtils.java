package fr.lauparr.pplanner.server.services.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SrvJpaUtils {

	private static final String SPLIT_CHAR = "\\.";
	@Autowired
	private SpelAwareProxyProjectionFactory projectionFactory;

	public <T> T convertToDto(final Object data, final Class<T> clazz) {
		return this.projectionFactory.createProjection(clazz, data);
	}

	public <T> List<T> convertListDto(final List<?> liste, final Class<T> clazz) {
		return liste.stream().map(x -> this.convertToDto(x, clazz)).collect(Collectors.toList());
	}

	public <T> Page<T> convertPageDto(final Page<?> page, final Class<T> clazz) {
		return page.map(x -> this.convertToDto(x, clazz));
	}

	public <T> T findRandom(final PagingAndSortingRepository<T, ?> repository) {
		final long count = repository.count();
		final int idx = new SecureRandom().nextInt((int) count);
		final List<T> result = repository.findAll(PageRequest.of(idx, 1)).getContent();
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}

	/**
	 * Récupère le Path à partir d'un Root
	 */
	public <T> Path<T> getPathFromRoot(final Root<T> root, final String field) {
		final String principal;
		String[] fields = null;
		if (field != null) {
			final String[] checks = field.split(SPLIT_CHAR);
			principal = checks[0];
			if (checks.length > 1) {
				fields = Arrays.copyOfRange(checks, 1, checks.length);
			}
		} else {
			principal = null;
		}
		final Path<T> p;
		if (this.isSubPath(fields)) {
			p = this.crossPathToPath(root.join(principal, JoinType.LEFT), fields);
		} else {
			p = root.get(principal);
		}
		return p;
	}

	/**
	 * Vérifie qu'il ne s'agit pas du dernier Path "extractible"
	 */
	private boolean isSubPath(final String[] fields) {
		return fields != null && fields.length > 0;
	}

	/**
	 * Récupère un Path à partir du Path et de la liste de champs filtrés
	 */
	private <T> Path<T> crossPathToPath(final Path<T> path, final String[] fields) {
		Path<T> lastPath = path;
		if (fields != null) {
			for (final String key : fields) {
				lastPath = lastPath.get(key);
			}
		}
		return lastPath;
	}

}
