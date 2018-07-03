package it.javaboss;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Profile;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.MultiValueMap;

public class AndConditionProfile implements Condition {
	
	public static final String VALUE = "value";
	public static final String DEFAULT_PROFILE = "default";

	@Override
	public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
		if (context.getEnvironment() == null) {
			return true;
		}
		MultiValueMap<String, Object> attrs = metadata.getAllAnnotationAttributes(Profile.class.getName());
		if (attrs == null) {
			return true;
		}

		Set<String> activeProfilesSet = 
				Arrays.stream(context.getEnvironment().getActiveProfiles())
					.collect(Collectors.toSet());
		String[] definedProfiles = (String[]) attrs.getFirst(VALUE);
		Set<String> allowedProfiles = new HashSet<>(1);
		Set<String> restrictedProfiles = new HashSet<>(1);
		
		if (activeProfilesSet.size() == 0) {
			activeProfilesSet.add(DEFAULT_PROFILE); // no profile is equivalent in @Profile terms to "default"
		}
		
		for (String nextDefinedProfile : definedProfiles) {
			if (!nextDefinedProfile.isEmpty() && nextDefinedProfile.charAt(0) == '!') {
				restrictedProfiles.add(nextDefinedProfile.substring(1, nextDefinedProfile.length()));
				continue;
			}
			allowedProfiles.add(nextDefinedProfile);
		}
		
		boolean allowed = true;
		for (String allowedProfile : allowedProfiles) {
			allowed = allowed && activeProfilesSet.contains(allowedProfile);
		}
		
		boolean restricted = true;
		for (String restrictedProfile : restrictedProfiles) {
			restricted = restricted && !activeProfilesSet.contains(restrictedProfile);
		}
		
		boolean activated = allowed && restricted;
		System.out.println( Arrays.asList( definedProfiles ) + " = " + activated );
		
		return activated;
	}

}
