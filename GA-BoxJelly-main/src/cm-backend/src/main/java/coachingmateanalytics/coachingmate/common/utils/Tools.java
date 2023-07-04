package coachingmateanalytics.coachingmate.common.utils;

import java.util.*;

/**
 * A utility class for various tools and helper methods.
 */
public class Tools {

    /**
     * Converts a Paging object to a Map with start and end numbers.
     *
     * @param paging The Paging object containing the current page and page size.
     * @return A Map containing the start and end numbers for pagination.
     */
    public static Map<String, Integer> ConvertPaging(Paging paging) {
        Integer startNum = (paging.getCurrentPage() - 1) * paging.getPageSize();
        Integer endNum = paging.getCurrentPage() * paging.getPageSize();
        Map<String, Integer> pagingMap = new HashMap<>();
        pagingMap.put("startNum", startNum);
        pagingMap.put("endNum", endNum);
        return pagingMap;
    }

    /**
     * Returns a random sublist of the given list with the specified number of elements.
     *
     * @param list The input list to select random elements from.
     * @param num  The number of elements to select.
     * @return A List containing the randomly selected elements.
     */
    public static List getRandomList(List list, int num) {
        List<Object> olist = new ArrayList<>();
        
        if (list.size() <= num) {
            return list;
        } else {
            Random random = new Random();
            
            for (int i = 0; i < num; i++) {
                int intRandom = random.nextInt(list.size() - 1);
                olist.add(list.get(intRandom));
                list.remove(list.get(intRandom));
            }
            
            return olist;
        }
    }
}
